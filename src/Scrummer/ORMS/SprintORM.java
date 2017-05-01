package Scrummer.ORMS;

import Scrummer.ActionHandlers.Backlog;
import Scrummer.ORM;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class SprintORM extends ORM {

    protected PreparedStatement createStatement;
    protected PreparedStatement startStatement;
    protected PreparedStatement planStatement;
    protected PreparedStatement getBacklogIdStatement;
    protected PreparedStatement getLastSprintStatement;

    protected void CreateStatements() throws SQLException {
        createStatement = link.prepareStatement("insert into Sprints (projectName, backlogId) values(?, ?)");
        planStatement = link.prepareStatement("update Sprints set title = ?, duration = ? where projectName = ?");
        getBacklogIdStatement = link.prepareStatement("select backlogId from Sprints where projectName = ?");
        getLastSprintStatement = link.prepareStatement("select * from Sprints where backlogId = ? order by creation_date desc");
        startStatement = link.prepareStatement("update Sprints set startDate = ? where title = ?");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        createStatement.close();
        planStatement.close();
        getBacklogIdStatement.close();
        getLastSprintStatement.close();
        startStatement.close();
    }

    protected int createQuery(String projectName, int backlogId) {
        try {
            createStatement.setString(1, projectName);
            createStatement.setInt(2, backlogId);
            return createStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SprintORM.createQuery(): " + ex.getMessage());
            return -1;
        }
    }

    protected int getBacklogIdQuery(String projectName) {
        try {
            getBacklogIdStatement.setString(1, projectName);
            ResultSet sprints = getBacklogIdStatement.executeQuery();
            sprints.next();
            if (!sprints.first()) {
                return -1;
            }

            return sprints.getInt(1);
        } catch (SQLException ex) {
            System.err.println("SprintORM.getBacklogIdQuery(): " + ex.getMessage());
            return -1;
        }
    }

    protected int planQuery(String title, int duration, String projectName) {
        try {
            planStatement.setString(1, title);
            planStatement.setInt(2, duration);
            planStatement.setString(3, projectName);
            return planStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SprintORM.getBacklogIdQuery(): " + ex.getMessage());
            return -1;
        }
    }

    protected int startQuery(String projectName) {
        try {
            int backlogId = getBacklogIdQuery(projectName);
            getLastSprintStatement.setInt(1, backlogId);
            ResultSet sprints = getLastSprintStatement.executeQuery();
            sprints.next();
            if (!sprints.first()) {
                return -1;
            }
            if (sprints.getInt(5) != 0) {
                Date date = sprints.getDate(4);
                if (sprints.wasNull()) {
                    startStatement.setTimestamp(1, java.sql.Timestamp.from(java.time.Instant.now()));
                    startStatement.setString(2, sprints.getString(1));
                    startStatement.execute();
                    apply();
                    return 1;
                }
            }
            return 0;
        } catch (SQLException ex) {
            System.err.println("SprintORM.getBacklogIdQuery(): " + ex.getMessage());
            return -1;
        }
    }
}
