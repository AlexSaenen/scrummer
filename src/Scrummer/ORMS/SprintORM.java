package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class SprintORM extends ORM {

    protected PreparedStatement createStatement;
//    protected PreparedStatement startStatement;
    protected PreparedStatement planStatement;
    protected PreparedStatement getCurrentBacklogIdStatement;

    protected void CreateStatements() throws SQLException {
        createStatement = link.prepareStatement("insert into Sprints (projectName, backlogId) values(?, ?)");
        planStatement = link.prepareStatement("update Sprints set title = ?, duration = ? where projectName = ? and creation_date = ?");
        getCurrentBacklogIdStatement = link.prepareStatement("select backlogId from Sprints where projectName = ? order by creation_date desc");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        createStatement.close();
        planStatement.close();
        getCurrentBacklogIdStatement.close();
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

    protected int getCurrentBacklogIdQuery(String projectName) {
        try {
            getCurrentBacklogIdStatement.setString(1, projectName);
            ResultSet sprints = getCurrentBacklogIdStatement.executeQuery();
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
//            planStatement.setTimestamp(4, date);
            return planStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SprintORM.getBacklogIdQuery(): " + ex.getMessage());
            return -1;
        }
    }

//    protected int startQuery(String projectName) {
//        try {
//            startStatement.setString(1, projectName);
//        } catch (SQLException ex) {
//            System.err.println("SprintORM.getBacklogIdQuery(): " + ex.getMessage());
//            return -1;
//        }
//    }
}
