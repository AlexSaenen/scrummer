package Scrummer.Controllers;

import Scrummer.ORMS.RoomORM;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Rooms extends RoomORM {

    private void displayRoomStatus(ResultSet room, boolean hasMeetings) throws SQLException {
        System.out.print("Room Name: " + room.getString(1));
        System.out.print(", Number: " + room.getInt(2));
        System.out.print(", Building: " + room.getString(3));
        System.out.print(", Phone: " + room.getString(4));
        System.out.print(", Projector: " + room.getString(5));
        System.out.println(", STATUS: " + (hasMeetings ? "occupied" : "available"));
    }

    public void getAllStatusFor(Date date) {
        ResultSet rooms = getAllQuery();
        try {
            System.out.print("\nStatus of all rooms for " + date + ":");
            if (!rooms.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    ResultSet meetings = getMeetingsForQuery(rooms.getString(1), date);
                    System.out.print("\t");
                    displayRoomStatus(rooms, meetings.first());
                } while (rooms.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean add(String roomName, int roomNumber, String buildingName, String phone, String projector) {
        return addQuery(roomName, roomNumber, buildingName, phone, projector) != -1;
    }
}
