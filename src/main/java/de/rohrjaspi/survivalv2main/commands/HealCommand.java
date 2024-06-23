package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 0) {
				player.setFoodLevel(20);
				player.setHealth(20);
				player.sendMessage(S.P + ChatColor.AQUA + " Du wurdest geheilt.");
			}

			if (args.length >= 1) {
				Player targetPlayer = Bukkit.getPlayer(args[0]);
				targetPlayer.setFoodLevel(20);
				targetPlayer.setHealth(20);

				player.sendMessage(S.P + ChatColor.AQUA + targetPlayer.getName() +" Wurde geheilt.");
			}


		} else {
			sender.sendMessage(S.P + ChatColor.RED + " Du kannst dies Command nicht benutzen.");
		}

		return false;
	}
}
