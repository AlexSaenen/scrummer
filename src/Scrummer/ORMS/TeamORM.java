package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class TeamORM extends ORM {

    protected PreparedStatement getStatement = null;
    protected PreparedStatement addStatement = null;

    public TeamORM() {
        try {
            CreateStatements();
            isPrepared = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void CreateStatements() throws SQLException {
        getStatement = link.prepareStatement("select * from Software_Teams where Software_team_name = ?");
        addStatement = link.prepareStatement("insert into Software_Teams values(?, ?, ?, ?)");
    }

    protected void CloseStatements() throws SQLException {
        getStatement.close();
        addStatement.close();
    }

    protected ResultSet getAllQuery() {
        try {
            stmnt = link.createStatement();
            ResultSet results = stmnt.executeQuery("select * from Software_Teams");
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected ResultSet getQuery(String teamName) {
        try {
            getStatement.setString(1, teamName);
            ResultSet results = getStatement.executeQuery();
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected int addQuery(String teamName, String leader, Date date, String projectName) {
        try {
            addStatement.setString(1, teamName);
            addStatement.setString(2, leader);
            addStatement.setDate(3, date);
            addStatement.setString(4, projectName);
            return addStatement.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.err.println("Cannot add, this team already exists");
            } else {
                System.err.println(ex.getMessage());
            }
            return -1;
        }
    }
}
