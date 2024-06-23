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


public class MoneyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Player p = (Player) sender;
            p.sendMessage(S.P + " §7Deine Münzen: §e$" + Maths.asString(PlayerSQL.getCoins(p.getUniqueId()))); // Maths.asString(pc.getCoins()
            return true;
        }
        if (args.length == 1) {
            Player p = (Player) sender;
            Player t = Bukkit.getPlayer(args[0]);
            if (t !=null) {
                p.sendMessage("§7"+ args[0] + "'s Münzen: §e$" + Maths.asString(PlayerSQL.getCoins(t.getUniqueId()))); // Maths.asString(pc2.getCoins()
                return true;
            }
            p.sendMessage("§7§bDieser Spieler ist nicht Online!");
            return true;
        }
        if (args.length == 2) {
            Player p = (Player) sender;
            if (!p.hasPermission("command.money")) {
                p.sendMessage(S.P + " §7Deine Münzen: §e$" + Maths.asString(PlayerSQL.getCoins(p.getUniqueId())));
                return true;
            }
            if (!args[0].equalsIgnoreCase("giveall")) {
                return true;
            }
            if (!Maths.isLong(args[1])) {
                return true;
            }
            long coins = Long.parseLong(args[1]);
            for (Player all : Bukkit.getOnlinePlayers()) {
                PlayerSQL.addCoins(all.getUniqueId(), coins);
            }
            Bukkit.broadcastMessage(S.P + " §7Alle Spieler haben §e$" + coins + " §7Münzen erhalten.");
            return true;
        }
        if (args.length == 3) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!p.hasPermission("command.money")) {
                    p.sendMessage(S.P + " §7Deine Münzen: §e$" + Maths.asString(PlayerSQL.getCoins(p.getUniqueId())));
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("add")) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
                if (Maths.isLong(args[2])) {
                    long coins = Long.parseLong(args[2]);
                    PlayerSQL.addCoins(t.getUniqueId(), coins);
                    if (sender instanceof Player) {
                        sender.sendMessage(S.P + " §7Du hast dem Spieler §6" + args[1] + " §e$" + Maths.asString(coins) + " §7Münzen gegeben.");
                    }
                    return true;
                }
                if (sender instanceof Player) {
                    sender.sendMessage("§b Es ist ein Fehler aufgetreten");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("set")) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
                if (Maths.isLong(args[2])) {
                    long coins = Long.parseLong(args[2]);
                    PlayerSQL.setCoins(t.getUniqueId(), coins);
                    sender.sendMessage(S.P + " §7Du hast die Münzen von Spieler §6" + args[1] + " auf §e$" + Maths.asString(coins) + " §7Münzen gesetzt.");

                    return true;
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
                if (Maths.isLong(args[2])) {
                    long coins = Long.parseLong(args[2]);
                    PlayerSQL.removeCoins(t.getUniqueId(), coins);
                    sender.sendMessage(S.P + " §7Du hast dem Spieler §6" + args[1] + " §e$" + Maths.asString(coins) + " §7Münzen entfernt.");
                }
                return true;
            }
        }
        return false;
    }
}
