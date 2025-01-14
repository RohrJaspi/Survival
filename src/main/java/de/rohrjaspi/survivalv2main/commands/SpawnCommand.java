package de.rohrjaspi.survivalv2main.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) return false;

		Player player = (Player) sender;

		Location spawn = player.getWorld().getSpawnLocation();

		spawn.setYaw(270f);

		player.teleport(spawn);


		return true;
	}
}
