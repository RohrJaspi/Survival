package de.rohrjaspi.survivalv2main.inventories.AdminShop;

import de.rohrjaspi.survivalv2main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminShopCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) return false;

		Player player = (Player) sender;

		new AdminShop(Main.getPlayerMenuUtility(player)).open();

		return true;
	}
}
