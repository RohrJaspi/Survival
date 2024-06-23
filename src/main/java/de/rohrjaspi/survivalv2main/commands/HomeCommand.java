package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.extra.ItemCreator;
import de.rohrjaspi.survivalv2main.home.Home;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String[] strings) {
        if(!(commandSender instanceof Player)) {
            return true;
        }
        Player p = (Player) commandSender;
        if(strings.length == 0) {
            if(PlayerSQL.getHomes(p.getUniqueId()) == null || PlayerSQL.getHomes(p.getUniqueId()).isEmpty()) {
                p.sendMessage(S.P + "Du hast noch keine Homes gesetzt. Nutze: §c/sethome <NAME>");
                return true;
            }
            p.openInventory(homes(p));
            return true;
        } else {
            if(strings.length != 2) {
                if(strings.length == 1) {
                    if(PlayerSQL.getHome(p.getUniqueId(), strings[0]) != null) {
                        Home h = PlayerSQL.getHome(p.getUniqueId(),strings[0]);
                        if(h == null || h.getLoc() == null) {
                            p.sendMessage(S.P + "§4Fehler: §cDas Home wurde nicht gefunden.");
                            return true;
                        }
                        p.teleport(h.getLoc());
                        return true;
                    } else {
                        p.sendMessage(S.P + "§cDas Home existiert nicht.");
                    }
                }
            }
            if(!p.hasPermission("survival.command.home")) {
                p.sendMessage(S.P + "Dazu hast du keine Rechte!");
                return true;
            }
            Player t = Bukkit.getPlayer(strings[0]);
            if(t == null) {
                p.sendMessage(S.P + "Dieser Spieler existiert nicht, oder ist nicht online!");
                return true;
            }
            if(PlayerSQL.getHome(p.getUniqueId(), strings[1]) != null) {
                p.teleport(PlayerSQL.getHome(p.getUniqueId(), strings[1]).getLoc());
                return true;
            }
            p.sendMessage(S.P + "§cDas Home von §4" + strings[0] + " §cexistiert nicht.");
        }
        return false;
    }

    public static Inventory homes(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*6, S.P + "Homes");
        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemCreator().material(Material.BLACK_STAINED_GLASS_PANE).displayName("§e ").build());
        }
        for(int j = inv.getSize() - 9; j < inv.getSize(); j++) {
            inv.setItem(j, new ItemCreator().material(Material.WHITE_STAINED_GLASS_PANE).displayName("§e ").build());
        }
        int slot = 10;
        for(Home home : PlayerSQL.getHomes(p.getUniqueId())) {
            if (slot == 17 || slot == 26 || slot == 35) {
                slot++;
                slot++;
            }
            inv.setItem(slot, new ItemCreator().material(Material.DARK_OAK_DOOR).displayName("§c" + home.getName()).lore(Arrays.asList("", "§8» §7Location: " + home.getLoc().getWorld().getName() + ", " + Double.valueOf(home.getLoc().getX()).shortValue() + ", " + Double.valueOf(home.getLoc().getY()).shortValue() + ", " + Double.valueOf(home.getLoc().getZ()).shortValue(), "§8» §7Linksklick zum teleportieren.","§8» §7Rechtsklick zum §4löschen.", "")).build());
            slot++;
        }
        return inv;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        Player p = (Player) sender;
        if (args.length == 1) {
            List<Home> homes = PlayerSQL.getHomes(p.getUniqueId());
            homes.forEach(home ->{
                completions.add(ChatColor.stripColor(home.getName()));
            });
            return completions;
        }


        return null;
    }
}
