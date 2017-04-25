package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alexsaenen on 4/19/17.
 */
public class BacklogORM extends ORM {
    @Override
    protected void CreateStatements() throws SQLException {

    }

    @Override
    protected void CloseStatements() throws SQLException {

    }

    protected int createQuery() {
        try {
            statement = link.createStatement();
            int result = statement.executeUpdate("insert into Backlogs values(null)", Statement.RETURN_GENERATED_KEYS);
            if (result != -1) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return -1;
                }
            }

            return -1;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -1;
        }
    }
}
