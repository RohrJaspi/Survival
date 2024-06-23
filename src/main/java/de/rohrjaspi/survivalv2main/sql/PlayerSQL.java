package de.rohrjaspi.survivalv2main.sql;

import de.rohrjaspi.survivalv2main.Main;
import de.rohrjaspi.survivalv2main.Utils.LocationUtil;
import de.rohrjaspi.survivalv2main.home.Home;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerSQL {

    public static boolean existsByName(String name) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM spieler WHERE NAME='" + name + "'");
            return rs.next() && rs.getString("NAME") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static UUID getUuidByName(String name) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM spieler WHERE NAME='" + name + "'");
            if(rs.next()) {
                return UUID.fromString(rs.getString("UUID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean playerExist(UUID uuid) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM spieler WHERE UUID='" + uuid.toString() + "'");
            return rs.next() && rs.getString("UUID") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExist(uuid)) {
            Main.getInstance().getMysql().update("INSERT INTO spieler(UUID, NAME, COINS, ID, IP, GEMS)" +
                    " VALUES ('" + uuid.toString() + "', '" + Bukkit.getPlayer(uuid).getName() + "','1000', '" + (newId() + 1) + "', '" + Bukkit.getPlayer(uuid).getAddress().getAddress().toString().replace("/", "") + "', '0')");
        }
    }

    public static Integer newId() {
        int id = 0;
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT UUID FROM spieler");
            while(rs.next()) {
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static Home getHome(UUID uuid, String name) {
        for (Home home : new HomeSQL(uuid).getHomes()) {
            if (home.getName().equals(name)) {
                return home;
            }
        }
        return null;
    }

    public static ArrayList<Home> getHomes(UUID uuid) {
        return new HomeSQL(uuid).getHomes();
    }

    public static void addHome(UUID uuid, String name, Location loc) {
        if (playerExist(uuid)) {
            HomeSQL homeSQL = new HomeSQL(uuid);
            ArrayList<Home> homes = homeSQL.getHomes();
            homes.add(new Home(name, loc));

            StringBuilder homeData = new StringBuilder();
            for (Home home : homes) {
                homeData.append(home.getName()).append("!").append(LocationUtil.locationToString(home.getLoc())).append("#");
            }
            if (homeData.length() > 0) {
                homeData.setLength(homeData.length() - 1);
            }

            String query = "UPDATE homes SET HOMES='" + homeData.toString() + "' WHERE UUID='" + uuid.toString() + "'";
            Main.getInstance().getMysql().update(query);
        }
    }

    public static void removeHome(UUID uuid, String name) {
        if(playerExist(uuid)) {
            HomeSQL homeSQL = new HomeSQL(uuid);
            ArrayList<Home> homes = homeSQL.getHomes();

            Home homeToRemove = null;
            for (Home home : homes) {
                if (home.getName().equalsIgnoreCase(name)) {
                    homeToRemove = home;
                    break;
                }
            }

            if (homeToRemove != null) {
                homes.remove(homeToRemove);

                StringBuilder homeData = new StringBuilder();
                for (Home home : homes) {
                    homeData.append(home.getName()).append("!").append(LocationUtil.locationToString(home.getLoc())).append("#");
                }
                if (homeData.length() > 0) {
                    homeData.setLength(homeData.length() - 1);
                }

                String query = "UPDATE homes SET HOMES='" + homeData.toString() + "' WHERE UUID='" + uuid.toString() + "'";
                Main.getInstance().getMysql().update(query);
            }
        }
    }


    public static Long getCoins(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM spieler WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getLong("COINS");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getCoins(uuid);
        }
        return null;
    }

    public static Integer getId(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM spieler WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getInt("ID");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getId(uuid);
        }
        return null;
    }


    public static String getIp(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM spieler WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getString("IP");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getIp(uuid);
        }
        return null;
    }



    public static Long getGems(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM spieler WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getLong("GEMS");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getGems(uuid);
        }
        return null;
    }

    public static void setCoins(UUID uuid, long coins) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE spieler SET COINS='" + coins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }

    public static void addCoins(UUID uuid, long coins) {
        setCoins(uuid, getCoins(uuid) + coins);
    }

    public static void removeCoins(UUID uuid, long coins) {
        setCoins(uuid, getCoins(uuid) - coins);
    }

    public static void setIp(UUID uuid, String ip) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE spieler SET IP='" + ip + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setIp(uuid, ip);
        }
    }

    public static void setGems(UUID uuid, long gems) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE spieler SET GEMS='" + gems + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setGems(uuid, gems);
        }
    }

    public static void addGems(UUID uuid, long gems) {
        setGems(uuid, getGems(uuid) + gems);
    }

    public static void removeGems(UUID uuid, long gems) {
        setGems(uuid, getGems(uuid) - gems);
    }

}
