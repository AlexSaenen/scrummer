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
    protected PreparedStatement getBacklogIdStatement;

    protected void CreateStatements() throws SQLException {
        addStatement = link.prepareStatement("insert into Sprints (projectName, backlogId) values(?, ?)");
        getBacklogIdStatement = link.prepareStatement("select backlogId from Sprints where projectName = ?");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        addStatement.close();
        getBacklogIdStatement.close();
    }

    protected int createQuery(String projectName, int backlogId) {
        try {
            addStatement.setString(1, projectName);
            addStatement.setInt(2, backlogId);
            return addStatement.executeUpdate();
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
}
