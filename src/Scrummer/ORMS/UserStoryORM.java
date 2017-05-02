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
    protected PreparedStatement getAllStatement;
    protected PreparedStatement getToDoStatement;
    protected PreparedStatement getBacklogIdStatement;
    protected PreparedStatement updateBacklogIdStatement;
    protected PreparedStatement updateABacklogIdStatement;
    protected PreparedStatement updateAllBacklogIdStatement;
    protected PreparedStatement getStatement;
    protected PreparedStatement removeStatement;
    protected PreparedStatement changeStatusStatement;

    protected void CreateStatements() throws SQLException {
        createStatement = link.prepareStatement("insert into UserStories (role, goal, reason, priority, class, backlogId) values(?, ?, ?, ?, ?, ?)");
        getAllStatement = link.prepareStatement("select * from UserStories where backlogId = ?");
        getBacklogIdStatement = link.prepareStatement("select backlogId from UserStories where id = ?");
        updateBacklogIdStatement = link.prepareStatement("update UserStories set backlogId = ? where id = ?");
        updateABacklogIdStatement = link.prepareStatement("update UserStories set backlogId = ? where id = ? and backlogId = ?");
        updateAllBacklogIdStatement = link.prepareStatement("update UserStories set backlogId = ? where backlogId = ?");
        getStatement = link.prepareStatement("select * from UserStories where id = ?");
        removeStatement = link.prepareStatement("delete from UserStories where id = ? and backlogId = ?");
        changeStatusStatement = link.prepareStatement("update UserStories set status = ?  where id = ?");
        getToDoStatement = link.prepareStatement("select * from UserStories where backlogId = ? and status = 0");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        createStatement.close();
        getAllStatement.close();
        getToDoStatement.close();
        getBacklogIdStatement.close();
        updateBacklogIdStatement.close();
        updateABacklogIdStatement.close();
        updateAllBacklogIdStatement.close();
        getStatement.close();
        changeStatusStatement.close();
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

            apply();
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

    protected int updateBacklogIdQuery(int storyId, int newBacklogId, int oldBacklogId) {
        try {
            PreparedStatement query;

            if (oldBacklogId == -1) {
                query = updateBacklogIdStatement;
            } else {
                query = updateABacklogIdStatement;
                query.setInt(3, oldBacklogId);
            }

            query.setInt(1, newBacklogId);
            query.setInt(2, storyId);
            int result = query.executeUpdate();

            if (oldBacklogId == -1 && result != -1) {
                link.commit();
            } else if (result == -1) {
                link.rollback();
            }

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

    protected ResultSet getAllQuery(int backlogId) {
        try {
            getAllStatement.setInt(1, backlogId);
            return getAllStatement.executeQuery();
        } catch (SQLException ex) {
            System.err.println("UserStoriesORM.getAllQuery(): " + ex.getMessage());
            return null;
        }
    }

    public int removeQuery(int userStoryId, int backlogId) {
        try {
            removeStatement.setInt(1, userStoryId);
            removeStatement.setInt(2, backlogId);
            int result = removeStatement.executeUpdate();

            if (result == -1) {
                cancel();
            } else {
                apply();
            }

            return result;
        }
        catch (SQLException ex) {
            System.err.println("ProjectORM.removeQuery(): " + ex.getMessage());
            cancel();
            return -1;
        }
    }

    public ResultSet getQuery(int userStoryId) {
        try {
            getStatement.setInt(1, userStoryId);
            return getStatement.executeQuery();
        }
        catch (SQLException ex) {
            System.err.println("ProjectORM.getQuery(): " + ex.getMessage());
            return null;
        }
    }

    public int changeStatusQuery(int userStoryId, int status) {
        try {
            changeStatusStatement.setInt(1, status - 1);
            changeStatusStatement.setInt(2, userStoryId);
            int result = changeStatusStatement.executeUpdate();
            apply();
            return result;
        } catch (SQLException e) {
                e.printStackTrace();
                cancel();
                return -1;
        }
    }

    public ResultSet getToDoQuery(int backlogId) {
        try {
            getToDoStatement.setInt(1, backlogId);
            return getToDoStatement.executeQuery();
        }
        catch (SQLException ex) {
            System.err.println("ProjectORM.getToDoQuery(): " + ex.getMessage());
            return null;
        }
    }

    public int updateAllBacklogIdQuery(int from, int to) {
        try {
            updateAllBacklogIdStatement.setInt(1, to);
            updateAllBacklogIdStatement.setInt(2, from);
            int result = updateAllBacklogIdStatement.executeUpdate();

            if (result == -1) {
                link.rollback();
            }

            return result;
        } catch (SQLException ex) {
            System.err.println("UserStoryORM.updateAllBacklogIdQuery(): " + ex.getMessage());
            try {
                link.rollback();
            } catch (SQLException rollbackError) {
                System.err.println("UserStoryORM.rollback(): " + rollbackError.getMessage());
            }
            return -1;
        }
    }
}
