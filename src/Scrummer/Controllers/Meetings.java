package Scrummer.Controllers;

import Scrummer.ORMS.MeetingORM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Meetings extends MeetingORM {

    private void displayMeetingInfo(ResultSet meeting) throws SQLException {
        System.out.print("Room Name: " + meeting.getString(3));
        System.out.print(", Meeting Date: " + meeting.getDate(1));
        System.out.println(", Purpose: " + meeting.getString(2));
    }

    private void displayMeetingInfoWithoutPurpose(ResultSet meeting) throws SQLException {
        System.out.print("Room Name: " + meeting.getString(3));
        System.out.print(", Team Name: " + meeting.getString(4));
        System.out.println(", Meeting Date: " + meeting.getDate(1));
    }

    public void getFor(String teamName, Date from, Date to) {
        ResultSet meetings = getForQuery(teamName, from, to);
        try {
            System.out.print("\nMEETINGS:");
            if (!meetings.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do  {
                    System.out.print("\t");
                    displayMeetingInfo(meetings);
                } while (meetings.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void getForPurpose(String purpose, Date from, Date to) {
        ResultSet meetings = getForPurposeQuery(purpose, from, to);
        try {
            System.out.print("\nMEETINGS with purpose '" + purpose + "':");
            if (!meetings.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do  {
                    System.out.print("\t");
                    displayMeetingInfoWithoutPurpose(meetings);
                } while (meetings.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void get(String teamName, String roomName, Date date) {
        ResultSet meetings = getQuery(teamName, roomName, date);
        try {
            meetings.next();
            if (!meetings.first()) {
                System.out.println(" None");
            } else {
                displayMeetingInfo(meetings);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean reserve(String teamName, String roomName, Date date, String purpose) {
        return reserveQuery(teamName, roomName, date, purpose) != -1;
    }

    public boolean moveBookings(String fromRoom, String toRoom, Date after) {
        return moveBookingsQuery(fromRoom, toRoom, after) != -1;
    }
}
