package Scrummer;

import java.sql.SQLException;

/**
 * Created by alexsaenen on 3/23/17.
 */
public abstract class ORM extends Database {

    public boolean isPrepared = false;

    protected void close() {
        try {
            stmnt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void finish() {
        try {
            CloseStatements();
            unlink();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract void CloseStatements() throws SQLException;
}
