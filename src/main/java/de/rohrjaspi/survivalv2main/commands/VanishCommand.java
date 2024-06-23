package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Main;
import de.rohrjaspi.survivalv2main.Utils.VanishManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

	VanishManager manager = Main.getInstance().getVanishManager();
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) return false;

			Player p = (Player) sender;

		if (!(manager.isVanished(p))) {
			manager.setVanished(p, true);
			p.sendMessage(ChatColor.GREEN + "Du bist nun im Vanish.");
		} else {
			manager.setVanished(p, false);
			p.sendMessage(ChatColor.GREEN + "Du bist nicht mehr im Vanish.");
		}

		return true;
	}
}
