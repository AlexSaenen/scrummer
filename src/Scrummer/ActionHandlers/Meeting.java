package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Meetings;
import Scrummer.UserInput;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Meeting extends ActionHandler {

    static private Meetings meetingsController = new Meetings();
    static private UserInput user = new UserInput();

    protected void enableActions() {
        actions = new String[]{"meeting", "reserveMeeting", "meetingsWithPurpose"};
    }

    protected void disable() {
        meetingsController.finish();
    }

    static public void getTeamMeetingsBetween(String teamName, Date from, Date to) {
        meetingsController.getFor(teamName, from, to);
    }

    static public boolean moveMeetingsBetween(String from, String to) {
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
        return meetingsController.moveBookings(from, to, currentDate);
    }

    @SuppressWarnings("unused")
    static public void meeting() {
        String teamName = user.getString("Team Name: ");
        String roomName = user.getString("Room Name: ");
        Date meetingDate = user.getDate("Meeting Date: ", true);
        System.out.println();
        meetingsController.get(teamName, roomName, meetingDate);
    }

    @SuppressWarnings("unused")
    static public void reserveMeeting() {
        String teamName = user.getString("Team Name: ");
        String roomName = user.getString("Room Name: ");

        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
        Date meetingDate;

        do {
            meetingDate = user.getDate("Meeting Date: ", true);
            if (meetingDate.before(currentDate)) {
                System.err.println("Expecting a future date");
            }
        } while (meetingDate.before(currentDate));

        String purpose = new String("empty");
        List<String> purposes = Arrays.asList("Planning", "Demo", "Team Working Session", "Other");

        while (purposes.contains(purpose) == false) {
            purpose = user.getString("Purpose " + purposes + ": ");
        }

        meetingsController.reserve(teamName, roomName, meetingDate, purpose);
    }

    @SuppressWarnings("unused")
    static public void meetingsWithPurpose() {
        String purpose = new String("empty");
        List<String> purposes = Arrays.asList("Planning", "Demo", "Team Working Session", "Other");

        while (purposes.contains(purpose) == false) {
            purpose = user.getString("Purpose " + purposes + ": ");
        }

        Date from = user.getDate("After (included): ", true);
        Date to = user.getDate("Before (included): ", true);

        meetingsController.getForPurpose(purpose, from, to);
    }
}