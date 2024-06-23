package de.rohrjaspi.survivalv2main.inventories.KistenShop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KistenShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) return  false;

        Player p = (Player) commandSender;

        p.openInventory(KistenShop.ChestPreview());

        return false;
    }
}
