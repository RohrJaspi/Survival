package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.home.Home;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DelhomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand( CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            return true;
        }
        Player p = (Player) commandSender;
        if(strings.length != 1) {
            p.sendMessage(S.P + "Verwendung: §e/delhome <Name>");
            return true;
        }
        if(PlayerSQL.getHome(p.getUniqueId(),strings[0]) != null) {
            PlayerSQL.removeHome(p.getUniqueId(), strings[0]);
            p.sendMessage(S.P + "Dein Home §e" + strings[0] + " §7wurde erfolgreich gelöscht.");
            return true;
        }
        p.sendMessage(S.P + "§cDas Home existiert nicht.");
        return false;
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
