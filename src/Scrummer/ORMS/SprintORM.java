package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class SprintORM extends ORM {

    protected PreparedStatement addStatement;

    protected void CreateStatements() throws SQLException {
        addStatement = link.prepareStatement("insert into Sprints (title, projectName, backlogId) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    protected void CloseStatements() throws SQLException {
        addStatement.close();
    }

    protected int createQuery(String projectName, int backlogId, String title) {
        try {
            addStatement.setString(1, title);
            addStatement.setString(2, projectName);
            addStatement.setInt(3, backlogId);
            int result = addStatement.executeUpdate();
            if (result != -1) {
                ResultSet rs = addStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return -1;
                }
            }

            return -1;
        } catch (SQLException ex) {
            System.err.println("SprintORM.createQuery(): " + ex.getMessage());
            return -1;
        }
    }
}
