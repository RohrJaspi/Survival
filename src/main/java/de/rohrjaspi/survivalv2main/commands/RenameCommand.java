package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission( "survival.command.rename")) {
            p.sendMessage(S.P + "Dazu hast du keine Rechte");
            return true;
        }
        if (strings.length == 0) {
            p.sendMessage(S.P + "Verwendung: §c/" + s + " <Itemname>");
            return true;
        }
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().equals(Material.AIR)) {
            p.sendMessage(S.P + "Halte das Item in der Hand.");
            return true;
        }
        ItemMeta meta = item.getItemMeta();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i] + " ");
        }
        String name = builder.toString().substring(0, builder.length() - 1);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        p.sendMessage(S.P + "Das Item wurde erfolgreich in " + builder.toString() + " §7umbenannt.");
        return false;
    }
}