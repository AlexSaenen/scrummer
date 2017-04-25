package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStoryORM extends ORM {

    protected PreparedStatement createStatement;
    protected PreparedStatement moveStatement;


    protected void CreateStatements() throws SQLException {
        createStatement = link.prepareStatement("insert into UserStories (role, goal, reason, priority, class, backlogId) values(?, ?, ?, ?, ?, ?)");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        createStatement.close();
    }

    protected int createQuery(String role, String goal, String reason, int priority, String aClass, int backlogId) {
        try {
            createStatement.setString(1, role);
            createStatement.setString(2, goal);
            createStatement.setString(3, reason);
            createStatement.setInt(4, priority);
            createStatement.setString(5, aClass);
            createStatement.setInt(6, backlogId);
            return createStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("UserStoryORM.createQuery(): " + ex.getMessage());
            return -1;
        }
    }

    protected ResultSet getAllQuery() {
        try {
            statement = link.createStatement();
            ResultSet results = statement.executeQuery("select * from UserStories");
            return results;
        } catch (SQLException ex) {
            System.err.println("UserStoriesORM.getAllQuery(): " + ex.getMessage());
            return null;
        }
    }
}
