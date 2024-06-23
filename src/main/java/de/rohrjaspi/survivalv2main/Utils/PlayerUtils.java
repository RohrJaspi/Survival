package de.rohrjaspi.survivalv2main.Utils;

import org.bukkit.Bukkit;

public class PlayerUtils {

    public static boolean isOnline(String name) {
        return Bukkit.getPlayerExact(name) != null && Bukkit.getPlayerExact(name).isOnline();
    }
}
