package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) return false;

		Player p = (Player) sender;

		p.sendMessage("§a ");
		p.sendMessage(S.P + "§8§m*                                                  *");
		p.sendMessage(S.P + " ");
		p.sendMessage(S.P + "Vote für uns §7auf:");
		p.sendMessage(S.P + "§chttps://outerdepths.de/vote");
		p.sendMessage(S.P + "");
		p.sendMessage(S.P + "§8§m*                                                  *");
		p.sendMessage("§a ");

		return true;
	}
}
