package de.rohrjaspi.survivalv2main.sql;


import java.sql.*;

public class MySQL {

    private Connection con;

    public boolean isConnected() {
        return con != null;
    }

    public MySQL() {
        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://u11_0yqvaAJoRE:LFh4%3DD..HWU19sZI2re91dkW@node-1.rohrjaspi.dev:3306/s11_OuterDepths-Dev"); // URL-STRING HERE
            System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
        } catch (SQLException e) {
            System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        } catch (SQLException e) {
            System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }

    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }
}
