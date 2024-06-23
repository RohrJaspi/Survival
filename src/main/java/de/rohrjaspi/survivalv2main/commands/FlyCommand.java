package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (!(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)) {
				if (!player.isFlying()) {
					player.setAllowFlight(true);
					player.sendMessage( S.P + ChatColor.AQUA + " Du kannst nun Fliegen.");
				} else {
					player.setAllowFlight(false);
					player.sendMessage(S.P + ChatColor.AQUA + " Du kannst nicht mehr Fliegen.");
				}
			}

		} else {
			sender.sendMessage(S.P + ChatColor.RED + " Du kannst dies Command nicht benutzen.");
		}

		return false;
	}
}
