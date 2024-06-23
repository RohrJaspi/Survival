package de.rohrjaspi.survivalv2main.commands;

import de.rohrjaspi.survivalv2main.Utils.Maths;
import de.rohrjaspi.survivalv2main.Utils.PlayerUtils;
import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (args.length != 2) {
            p.sendMessage("Verwendung: §e/" + label + " <Name> <Anzahl>");
            return true;
        }
        if (p.getName().equalsIgnoreCase(args[0])) {
            p.sendMessage(S.P + " §cDer Spieler kann nicht du selbst sein.");
            return true;
        }
        if (!PlayerUtils.isOnline(args[0])) {
            p.sendMessage(S.P + " §7§bDieser Spieler ist nicht Online!");
            return true;
        }
        Player t = Bukkit.getPlayer(args[0]);
        if (!Maths.isLong(args[1])) {
            p.sendMessage(S.P + " §7§bDu musst eine gültige Zahl angeben.");
            return true;
        }
        long anzahl = Long.parseLong(args[1]);
        if (PlayerSQL.getCoins(p.getUniqueId()) >= anzahl) {
            PlayerSQL.removeCoins(p.getUniqueId(), anzahl);
            PlayerSQL.addCoins(t.getUniqueId(), anzahl);
            p.sendMessage(S.P + " §7Du hast §6" + args[0] + " §b$" + Maths.asString(anzahl) + " §7Münzen überwiesen.");
            Bukkit.getPlayerExact(args[0]).sendMessage(S.P + " §7Du hast §b$" + Maths.asString(anzahl) + " §7Münzen von §b" + p.getName() + " §7erhalten.");
        }
        return false;
    }
}
