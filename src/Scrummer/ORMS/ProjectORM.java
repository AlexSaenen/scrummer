package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class ProjectORM extends ORM{

    protected PreparedStatement addStatement = null;
    protected PreparedStatement getStatement = null;

    protected void CreateStatements() throws SQLException {
        getStatement = link.prepareStatement("select * from Projects where name = ?");
        addStatement = link.prepareStatement("insert into Projects values(?, ?, ?, ?, ?)");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        getStatement.close();
        addStatement.close();
    }

    protected int addQuery(String projectName, Date dueDate, Date creationDate, String description, int backlogId) {
        try {
            System.out.println("Heyo");
            addStatement.setString(1, projectName);
            System.out.println("Heyo");
            addStatement.setDate(2, dueDate);
            addStatement.setDate(3, creationDate);
            System.out.println("Heyo");
            addStatement.setString(4, description);
            System.out.println("Heyoa");
            addStatement.setInt(5, backlogId);
            System.out.println("adding");
            return addStatement.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.err.println("Cannot add, this project already exists");
            } else {
                System.err.println(ex.getMessage());
            }
            return -1;
        }
    }

    protected ResultSet getAllQuery() {
        try {
            statement = link.createStatement();
            ResultSet results = statement.executeQuery("select * from Projects");
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected ResultSet getQuery(String projectName) {
        try {
            getStatement.setString(1, projectName);
            ResultSet results = getStatement.executeQuery();
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
