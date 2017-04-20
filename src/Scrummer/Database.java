package Scrummer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Database {
    static private String connectionUrl = "jdbc:mysql://192.168.56.101:3306/scrummer?user=cecs323b&password=cecs323";
    static protected Connection link = null;
    static public boolean isReady = false;
    static private int activeControllers = 0;

    protected Statement statement = null;

    public Database() {
        if (isReady == false && activeControllers == 0) {
            if (CreateConnection()) {
                isReady = true;
            }
        }

        activeControllers++;
    }

    private boolean CreateConnection() {
        try {
            System.out.println("Trying to connect to database ...");
            link = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            System.err.println("ConnectionError: " + ex.getMessage());
        } finally {
            return link != null;
        }
    }

    private void shutdown() {
        try {
            if (link != null) {
                link.close();
            }
        } catch (SQLException ex) {
            System.err.println("Database.shutdown(): " + ex.getMessage());
        }
    }

    public void unlink() {
        activeControllers--;

        if (activeControllers == 0) {
            shutdown();
        }
    }
}
