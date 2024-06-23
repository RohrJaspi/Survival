package de.rohrjaspi.survivalv2main.sql;

import de.rohrjaspi.survivalv2main.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BelohnungSQL {


    public static boolean playerExist(UUID uuid) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM belohnung WHERE UUID='" + uuid.toString() + "'");
            return rs.next() && rs.getString("UUID") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExist(uuid)) {
            Main.getInstance().getMysql().update("INSERT INTO belohnung(UUID, DAILY, WEEKLY, MONTHLY)" +
                    " VALUES ('" + uuid.toString() + "', '0','0','0')");
        }
    }

    public static Long getDaily(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM belohnung WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getLong("DAILY");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getDaily(uuid);
        }
        return 0L;
    }

    public static Long getWeekly(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM belohnung WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getLong("WEEKLY");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getWeekly(uuid);
        }
        return 0L;
    }

    public static Long getMonthly(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM belohnung WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getLong("MONTHLY");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getMonthly(uuid);
        }
        return 0L;
    }

    public static void setDaily(UUID uuid, long time) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE belohnung SET DAILY='" + time + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setDaily(uuid, time);
        }
    }

    public static void setWeekly(UUID uuid, long time) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE belohnung SET WEEKLY='" + time + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setWeekly(uuid, time);
        }
    }

    public static void setMonthly(UUID uuid, long time) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE belohnung SET MONTHLY='" + time + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setMonthly(uuid, time);
        }
    }

}
