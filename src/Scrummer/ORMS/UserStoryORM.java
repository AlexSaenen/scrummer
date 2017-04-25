package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStoryORM extends ORM {

    protected PreparedStatement addStatement;

    protected void CreateStatements() throws SQLException {
        addStatement = link.prepareStatement("insert into UserStories (status, role, goal, reason, priority, class, backlogId) values(?, ?, ?, ?, ?, ?, ?)");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        addStatement.close();
    }

    protected int addQuery(int status, String role, String goal, String reason, int priority, String aClass, int backlogId) {
        try {
            addStatement.setInt(1, status);
            addStatement.setString(2, role);
            addStatement.setString(3, goal);
            addStatement.setString(4, reason);
            addStatement.setInt(5, priority);
            addStatement.setString(6, aClass);
            addStatement.setInt(7, backlogId);
            return addStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -1;
        }
    }
}
