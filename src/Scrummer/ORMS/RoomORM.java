package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class RoomORM extends ORM {

    protected PreparedStatement getMeetingsForStatement = null;
    protected PreparedStatement addStatement = null;

    public RoomORM() {
        try {
            CreateStatements();
            isPrepared = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void CreateStatements() throws SQLException {
        getMeetingsForStatement = link.prepareStatement("select * from Meetings where Room_name = ? and Meeting_date = ?");
        addStatement = link.prepareStatement("insert into Conference_rooms values(?, ?, ?, ?, ?)");
    }

    protected void CloseStatements() throws SQLException {
        getMeetingsForStatement.close();
        addStatement.close();
    }

    protected ResultSet getAllQuery() {
        try {
            stmnt = link.createStatement();
            ResultSet results = stmnt.executeQuery("select * from Conference_rooms");
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected ResultSet getMeetingsForQuery(String roomName, Date date) {
        try {
            getMeetingsForStatement.setString(1, roomName);
            getMeetingsForStatement.setDate(2, date);
            ResultSet results = getMeetingsForStatement.executeQuery();
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected int addQuery(String roomName, int roomNumber, String buildingName, String phone, String projector) {
        try {
            addStatement.setString(1, roomName);
            addStatement.setInt(2, roomNumber);
            addStatement.setString(3, buildingName);
            addStatement.setString(4, phone);
            addStatement.setString(5, projector);
            return addStatement.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.err.println("Cannot add, this room already exists");
            } else {
                System.err.println(ex.getMessage());
            }
            return -1;
        }
    }
}
