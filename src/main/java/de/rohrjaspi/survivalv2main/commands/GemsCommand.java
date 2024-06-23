package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.Maths;
import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GemsCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

				if (!(sender instanceof Player)) {
					return true;
				}
				Player p = (Player) sender;
				if (args.length == 0) {
					p.sendMessage(S.P + " §7Deine Gems: §e$" + Maths.asString(PlayerSQL.getGems(p.getUniqueId()))); // Maths.asString(pc.getCoins()
					return true;
				}
				if (args.length == 1) {
					Player t = Bukkit.getPlayer(args[0]);
					if (t !=null) {
						p.sendMessage("§7"+ args[0] + "'s Gems: §e$" + Maths.asString(PlayerSQL.getGems(t.getUniqueId()))); // Maths.asString(pc2.getCoins()
						return true;
					}
					p.sendMessage("§7§bDieser Spieler ist nicht Online!");
					return true;
				}
				if (args.length == 2) {
					if (!p.hasPermission("command.money")) {
						p.sendMessage(S.P + " §7Deine Münzen: §e$" + Maths.asString(PlayerSQL.getGems(p.getUniqueId())));
						return true;
					}
					if (!args[0].equalsIgnoreCase("giveall")) {
						return true;
					}
					if (!Maths.isLong(args[1])) {
						return true;
					}
					long gems = Long.parseLong(args[1]);
					for (Player all : Bukkit.getOnlinePlayers()) {
						PlayerSQL.addGems(all.getUniqueId(), gems);
					}
					Bukkit.broadcastMessage(S.P + " §7Alle Spieler haben §e$" + gems + " §7Gems erhalten.");
					return true;
				}
				if (args.length == 3) {
					if (!p.hasPermission("command.money")) {
						p.sendMessage(S.P + " §7Deine Gems: §e$" + Maths.asString(PlayerSQL.getGems(p.getUniqueId())));
						return true;
					}
					if (args[0].equalsIgnoreCase("give")) {
						OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
						if (Maths.isLong(args[2])) {
							long gems = Long.parseLong(args[2]);
							PlayerSQL.addGems(t.getUniqueId(), gems);
							p.sendMessage(S.P + " §7Du hast dem Spieler §6" + args[1] + " §e$" + Maths.asString(gems) + " §7Gems gegeben.");
							return true;
						}
						p.sendMessage("§b Es ist ein Fehler aufgetreten");
						return true;
					}
					if (args[0].equalsIgnoreCase("set")) {
						OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
						if (Maths.isLong(args[2])) {
							long gems = Long.parseLong(args[2]);
							PlayerSQL.setGems(t.getUniqueId(), gems);
							p.sendMessage(S.P + " §7Du hast die Gems von Spieler §6" + args[1] + " auf §e$" + Maths.asString(gems) + " §7Gems gesetzt.");

							return true;
						}
						return true;
					}
					if (args[0].equalsIgnoreCase("remove")) {
						OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
						if (Maths.isLong(args[2])) {
							long gems = Long.parseLong(args[2]);
							PlayerSQL.removeGems(t.getUniqueId(), gems);
							p.sendMessage(S.P + " §7Du hast dem Spieler §6" + args[1] + " §e$" + Maths.asString(gems) + " §7Gems entfernt.");
						}
						return true;
					}
				}
				return false;
			}
		}
