package de.rohrjaspi.survivalv2main.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class AnitLagCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) return false;

		int removedItems = 0;

		for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
			if (entity instanceof Item) {
				entity.remove();
				removedItems++;
			}
		}

		sender.sendMessage(ChatColor.GREEN + "Antilag ausgeführt!");
		sender.sendMessage(ChatColor.YELLOW + "Antilag hat " + removedItems + " entfernt!");

		return true;
	}
}
