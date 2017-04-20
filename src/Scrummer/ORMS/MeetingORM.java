package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class MeetingORM extends ORM {

    protected PreparedStatement getForStatement = null;
    protected PreparedStatement getStatement = null;
    protected PreparedStatement reserveStatement = null;
    protected PreparedStatement getForPurposeStatement = null;
    protected PreparedStatement moveBookingsStatement = null;

    protected void CreateStatements() throws SQLException {
        getForStatement = link.prepareStatement("select * from Meetings where Software_team_name = ? and Meeting_date between ? and ? order by Meeting_date");
        getStatement = link.prepareStatement("select * from Meetings where Software_team_name = ? and Room_name = ? and Meeting_date = ?");
        reserveStatement = link.prepareStatement("insert into Meetings values(?, ?, ?, ?)");
        getForPurposeStatement = link.prepareStatement("select * from Meetings where Purpose_of_meeting = ? and Meeting_date between ? and ? order by Meeting_date");
        moveBookingsStatement = link.prepareStatement("update Meetings set Room_name = ? where Room_name = ? and Meeting_date >= ?");
    }

    protected void CloseStatements() throws SQLException {
        getForStatement.close();
        getStatement.close();
        reserveStatement.close();
        getForPurposeStatement.close();
        moveBookingsStatement.close();
    }

    protected ResultSet getForQuery(String teamName, Date from, Date to) {
        try {
            getForStatement.setString(1, teamName);
            getForStatement.setDate(2, from);
            getForStatement.setDate(3, to);
            ResultSet results = getForStatement.executeQuery();
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected ResultSet getForPurposeQuery(String purpose, Date from, Date to) {
        try {
            getForPurposeStatement.setString(1, purpose);
            getForPurposeStatement.setDate(2, from);
            getForPurposeStatement.setDate(3, to);
            ResultSet results = getForPurposeStatement.executeQuery();
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected ResultSet getQuery(String teamName, String roomName, Date date) {
        try {
            getStatement.setString(1, teamName);
            getStatement.setString(2, roomName);
            getStatement.setDate(3, date);
            ResultSet results = getStatement.executeQuery();
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected int reserveQuery(String teamName, String roomName, Date date, String purpose) {
        try {
            reserveStatement.setDate(1, date);
            reserveStatement.setString(2, purpose);
            reserveStatement.setString(3, roomName);
            reserveStatement.setString(4, teamName);
            return reserveStatement.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.err.println("Cannot reserve, this room is already taken that day");
            } else {
                System.err.println(ex.getMessage());
            }
            return -1;
        }
    }

    protected int moveBookingsQuery(String fromRoom, String toRoom, Date after) {
        try {
            moveBookingsStatement.setString(1, toRoom);
            moveBookingsStatement.setString(2, fromRoom);
            moveBookingsStatement.setDate(3, after);
            return moveBookingsStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -1;
        }
    }
}
