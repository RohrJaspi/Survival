package de.rohrjaspi.survivalv2main.inventories.Belohnung;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BelohnungCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Player p = (Player) commandSender;

        p.openInventory(Belohnung.RewardInv());

        return false;
    }
}
