package de.rohrjaspi.survivalv2main.inventories.SocialMenu;

import de.rohrjaspi.survivalv2main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SocialMediaCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

		if (!(sender instanceof Player)) return false;

		Player player = (Player) sender;

		new SocialMenu(Main.getPlayerMenuUtility(player)).open();

		return true;
	}
}
