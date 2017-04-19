package Scrummer;

import java.util.Arrays;


/**
 * Created by alexsaenen on 3/23/17.
 */
public class Menu {

    static private UserInput user = new UserInput();
    static private String[] userAllowedRequests = new String[] {
            "quit", "allTeams", "team", "addTeam",
            "meeting", "reserveMeeting", "meetingsWithPurpose",
            "roomsStatus", "addRoom"
    };

    static private void display() {
        System.out.println("\n========================");
        System.out.println("Menu Actions:");
        System.out.println("\t(quit) => Exit program");
        System.out.println("\nTeam Actions:");
        System.out.println("\t(allTeams) => List all teams");
        System.out.println("\t(team) => List a team and its meetings between a date range");
        System.out.println("\t(addTeam) => Add a new software development team");
        System.out.println("\nMeeting Actions:");
        System.out.println("\t(meeting) => List a meeting");
        System.out.println("\t(meetingsWithPurpose) => List all meetings that have a particular purpose");
        System.out.println("\t(reserveMeeting) => Create a future meeting");
        System.out.println("\nRoom Actions:");
        System.out.println("\t(roomsStatus) => List all rooms and their status for a particular date");
        System.out.println("\t(addRoom) => Add a new room and move all bookings from an other room to this one");
        System.out.println();
    }

    static private boolean isAllowed(String input) {
        return Arrays.asList(userAllowedRequests).contains(input);
    }

    static private String getInput() {
        return user.getString("What is your request ?: ");
    }

    static public String expose() {
        display();
        String request = null;

        while (request == null) {
            request = getInput();
            if (isAllowed(request) == false) {
                System.err.println("'" + request + "' is not a known request");
                request = null;
            }
        }

        return request;
    }
}
