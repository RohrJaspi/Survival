package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvSeeCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;

		Player player = (Player) sender;

		if (args.length < 1) {
			player.sendMessage(S.P + ChatColor.RED + "Nutze: /invsee <player>");
			return false;
		}

		Player targetPlayer = Bukkit.getPlayer(args[0]);

		if (targetPlayer == null) {
			player.sendMessage(S.P + "Player not found!");
			return false;
		}

		Inventory tInv = targetPlayer.getInventory();
		player.openInventory(tInv);

		return true;
	}
}

