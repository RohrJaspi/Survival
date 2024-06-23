package de.rohrjaspi.survivalv2main.sql;


import de.rohrjaspi.survivalv2main.Main;
import de.rohrjaspi.survivalv2main.Utils.LocationUtil;
import de.rohrjaspi.survivalv2main.home.Home;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class HomeSQL {

    private UUID uuid;

    public HomeSQL(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean playerExist() {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM homes WHERE UUID='" + uuid.toString() + "'");
            return rs.next() && rs.getString("UUID") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createPlayer() {
        if(!playerExist()) {
            Main.getInstance().getMysql().update("INSERT INTO homes(UUID, HOMES) VALUES ('" + uuid.toString() + "', '')");
        }
    }

    public ArrayList<Home> getHomes() {
        ArrayList<Home> homes = new ArrayList<>();
        if(playerExist()) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM homes WHERE UUID='" + uuid.toString() + "'");
                if(rs.next()) {
                    String home = rs.getString("HOMES");
                    if(!home.equals("")) {
                        for(String a : home.split("#")) {
                            Home b = new Home(a.split("!")[0], LocationUtil.stringToLocation(a.split("!")[1]));
                            homes.add(b);
                        }
                    }
                }
                return homes;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer();
        }
        return null;
    }
}
