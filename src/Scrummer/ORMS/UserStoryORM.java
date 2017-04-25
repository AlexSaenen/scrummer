package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStoryORM extends ORM {

    protected PreparedStatement createStatement;

    protected void CreateStatements() throws SQLException {
        createStatement = link.prepareStatement("insert into UserStories (status, role, goal, reason, priority, class, backlogId) values(?, ?, ?, ?, ?, ?, ?)");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        createStatement.close();
    }

    protected int createQuery(String role, String goal, String reason, int priority, String aClass, int backlogId) {
        try {
            createStatement.setInt(1, 0);
            createStatement.setString(2, role);
            createStatement.setString(3, goal);
            createStatement.setString(4, reason);
            createStatement.setInt(5, priority);
            createStatement.setString(6, aClass);
            createStatement.setInt(7, backlogId);
            return createStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("UserStoryORM.createQuery(): " + ex.getMessage());
            return -1;
        }
    }
}
