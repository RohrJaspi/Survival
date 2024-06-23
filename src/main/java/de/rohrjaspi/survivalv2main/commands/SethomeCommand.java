package de.rohrjaspi.survivalv2main.commands;


import de.rohrjaspi.survivalv2main.Utils.S;
import de.rohrjaspi.survivalv2main.sql.PlayerSQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SethomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String[] strings) {
        if(!(commandSender instanceof Player)) {
            return true;
        }
        Player p = (Player) commandSender;
        if(strings.length != 1) {
            p.sendMessage(S.P + "Verwendung: §e/sethome <Name>");
            return true;
        }
        if(!isValid(strings[0])) {
            p.sendMessage(S.P + "§cDer Name des Homes darf nur Zeichen von A-Z & 1-9 beinhalten.");
            return true;
        }
        if(strings[0].length() >= 16) {
            p.sendMessage(S.P + "§cDer Name des Homes darf max. 15 Zeichen lang sein.");
            return true;
        }
        int maxHomes = 3;
        if(p.hasPermission("citybuild.homes.5")) {
            maxHomes = 5;
        } else if(p.hasPermission("citybuild.homes.7")) {
            maxHomes = 7;
        } else if(p.hasPermission("citybuild.homes.10")) {
            maxHomes = 10;
        }
        if (maxHomes < PlayerSQL.getHomes(p.getUniqueId()).size()+1) {
            p.sendMessage(S.P + "Du hast schon mehr als §c" + maxHomes + "§7 Homes gesetzt!");
            return false;
        }

        PlayerSQL.addHome(p.getUniqueId(), strings[0], p.getLocation());
        p.sendMessage(S.P + "Das Home §e" + strings[0] + " §7wurde erstellt.");
        return false;
    }

    public boolean isValid(String code) {
        return code.matches("[aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789_-]*");
    }

}

