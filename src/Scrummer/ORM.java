package Scrummer;

import java.sql.SQLException;

/**
 * Created by alexsaenen on 3/23/17.
 */
public abstract class ORM extends Database {

    public boolean isPrepared = false;

    public ORM() {
        if (isReady) {
            try {
                CreateStatements();
                isPrepared = true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    protected void close() {
        try {
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void finish() {
        try {
            if (isPrepared) {
                CloseStatements();
            }

            unlink();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract void CreateStatements() throws SQLException;
    protected abstract void CloseStatements() throws SQLException;
}
