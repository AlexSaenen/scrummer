package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.*;

/**
 * Created by alexsaenen on 4/19/17.
 */
public class BacklogORM extends ORM {

    protected PreparedStatement createStatement;

    @Override
    protected void CreateStatements() throws SQLException {
        createStatement = link.prepareStatement("insert into Backlogs (type) values(?)", Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    protected void CloseStatements() throws SQLException {

    }

    protected int createQuery(boolean isSprint) {
        try {
            createStatement.setBoolean(1, isSprint);
            int result = createStatement.executeUpdate();
            if (result != -1) {
                ResultSet rs = createStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return -1;
                }
            }

            return -1;
        } catch (SQLException ex) {
            System.err.println("BacklogORM.createQuery(): " + ex.getMessage());
            return -1;
        }
    }
}
