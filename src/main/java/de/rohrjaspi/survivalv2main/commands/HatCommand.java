package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        if (!p.hasPermission("survival.hat")) {
            p.sendMessage(S.P + "Dazu hast du keine Rechte");
            return false;
        }

        if (p.getItemInHand().getType() != Material.AIR) {
            ItemStack helmet = p.getItemInHand();
            p.getInventory().setItemInMainHand(p.getInventory().getHelmet());
            p.getInventory().setHelmet(helmet);
            p.playSound(p, Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            p.sendMessage(S.P + "Du hast das Item auf den Kopf gezogen");
        } else {
            p.sendMessage(S.P + "Du musst ein Item in der Hand halten!");
        }

        return false;
    }
}
