package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStoryORM extends ORM {

    protected PreparedStatement createStatement;
    protected PreparedStatement getBacklogIdStatement;
    protected PreparedStatement updateBacklogIdStatement;

    protected void CreateStatements() throws SQLException {
        createStatement = link.prepareStatement("insert into UserStories (role, goal, reason, priority, class, backlogId) values(?, ?, ?, ?, ?, ?)");
        getBacklogIdStatement = link.prepareStatement("select backlogId from UserStories where id = ?");
        updateBacklogIdStatement = link.prepareStatement("update UserStories set backlogId = ? where id = ?");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        createStatement.close();
        getBacklogIdStatement.close();
        updateBacklogIdStatement.close();
    }

    protected int createQuery(String role, String goal, String reason, int priority, String aClass, int backlogId) {
        try {
            createStatement.setString(1, role);
            createStatement.setString(2, goal);
            createStatement.setString(3, reason);
            createStatement.setInt(4, priority);
            createStatement.setString(5, aClass);
            createStatement.setInt(6, backlogId);
            int result = createStatement.executeUpdate();

            link.commit();
            return result;
        } catch (SQLException ex) {
            System.err.println("UserStoryORM.createQuery(): " + ex.getMessage());
            try {
                link.rollback();
            } catch (SQLException rollbackError) {
                System.err.println("UserStoryORM.rollback(): " + rollbackError.getMessage());
            }
            return -1;
        }
    }

    protected int getBacklogIdQuery(int storyId) {
        try {
            getBacklogIdStatement.setInt(1, storyId);
            ResultSet stories = getBacklogIdStatement.executeQuery();
            stories.next();
            if (!stories.first()) {
                return -1;
            }

            return stories.getInt(1);
        } catch (SQLException ex) {
            System.err.println("UserStoryORM.getBacklogIdQuery(): " + ex.getMessage());
            return -1;
        }
    }

    protected int updateBacklogIdQuery(int storyId, int backlogId) {
        try {
            updateBacklogIdStatement.setInt(1, backlogId);
            updateBacklogIdStatement.setInt(2, storyId);
            int result = updateBacklogIdStatement.executeUpdate();

            link.commit();
            return result;
        } catch (SQLException ex) {
            System.err.println("UserStoryORM.updateBacklogQuery(): " + ex.getMessage());
            try {
                link.rollback();
            } catch (SQLException rollbackError) {
                System.err.println("UserStoryORM.rollback(): " + rollbackError.getMessage());
            }
            return -1;
        }
    }
}
