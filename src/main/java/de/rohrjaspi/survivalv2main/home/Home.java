package de.rohrjaspi.survivalv2main.home;

import org.bukkit.Location;
import org.bukkit.Material;

public class Home {

    private String name;

    private Location loc;

    public Home(String name, Location loc) {
        this.name = name;
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public boolean isSafe() {
        return !loc.getBlock().getType().equals(Material.AIR);
    }

}
