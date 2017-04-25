package Scrummer;

import java.sql.SQLException;
import java.sql.Savepoint;

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

    public void cancel() {
        try {
            link.rollback();
        } catch (SQLException rollbackError) {
            System.err.println("UserStoryORM.rollback(): " + rollbackError.getMessage());
        }
    }

    public void cancel(Savepoint save) {
        try {
            link.rollback(save);
        } catch (SQLException rollbackError) {
            System.err.println("UserStoryORM.rollback(): " + rollbackError.getMessage());
        }
    }

    public void apply() {
        try {
            link.commit();
        } catch (SQLException rollbackError) {
            System.err.println("UserStoryORM.rollback(): " + rollbackError.getMessage());
        }
    }

    protected abstract void CreateStatements() throws SQLException;
    protected abstract void CloseStatements() throws SQLException;
}
