package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Rooms;
import Scrummer.UserInput;

import java.sql.Date;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Room extends ActionHandler {

    static private Rooms roomsController = new Rooms();
    static private UserInput user = new UserInput();

    protected void enableActions() {
        actions = new String[] {"roomsStatus", "addRoom"};
    }
    protected void disable() {
        roomsController.finish();
    }

    @SuppressWarnings("unused")
    static public void roomsStatus() {
        Date meetingDate = user.getDate("Date: ", true);
        roomsController.getAllStatusFor(meetingDate);
    }

    @SuppressWarnings("unused")
    static public void addRoom() {
        String roomName = user.getString("Room name: ");
        int roomNumber = user.getInt("Room number: ");
        String buildingName = user.getString("Building name: ");
        String phone = user.getString("Phone: ");
        String projectorType = user.getString("Projector type: ");
        if (roomsController.add(roomName, roomNumber, buildingName, phone, projectorType)) {
            String oldRoom = user.getString("Room name from which to import the meetings: ");
            Meeting.moveMeetingsBetween(oldRoom, roomName);
        }
    }
}
