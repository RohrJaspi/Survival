package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TrollCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(S.P + "Nur spieler können diesen Command ausführen.");
			return true;
		}


		Player player = (Player) sender;

		if (!player.getName().equals("RohrJaspi")) return false;


		if (args.length == 1) {
			Player targetPlayer = Bukkit.getPlayer(args[0]);
			if (targetPlayer != null) {
				Location loc = targetPlayer.getLocation();

				if (targetPlayer.getGameMode() != GameMode.SURVIVAL) {
					for (int i = 0; i < 5; i++) {
						player.getWorld().spawnEntity(loc, EntityType.CREEPER);
					}
				} else {
					player.sendMessage(S.P + "Den Spieler den du trollen wolltest ist nicht Online oder existiert nicht.");
				}
			}
		}
		return true;
	}
}
