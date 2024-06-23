package de.rohrjaspi.survivalv2main.listeners;

import de.rohrjaspi.survivalv2main.Main;
import de.rohrjaspi.survivalv2main.Utils.S;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Random;

public class WeatherEventListener implements Listener {


    @EventHandler
    public void onRain(WeatherChangeEvent e) {

        if (e.toWeatherState()) {
            Random random = new Random();
            if (random.nextInt(10) == 2) {
                Bukkit.broadcastMessage(S.P + "Ein Sauerer Regen hat gestartet!");
                Main.sourrain = true;
            }
        } else if (Main.sourrain && !e.toWeatherState()) {
            Main.sourrain = false;
            Bukkit.broadcastMessage(S.P + "Der Saure Regen hat aufgehört!");
        }
    }



    public static boolean isUnderRain(Player p) {
            for (int i = 1; i <= 70; i++) {
                if (!p.getLocation().add(0, i, 0).getBlock().isPassable()) {
                    return true;
                }
            }
            return false;
    }
}