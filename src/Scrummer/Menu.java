package Scrummer;

import java.util.Arrays;


/**
 * Created by alexsaenen on 3/23/17.
 */
public class Menu {

    static private UserInput user = new UserInput();
    static private String[] userAllowedRequests = new String[] {
            "quit"
    };

    static private void display() {
        System.out.println("\n========================");
        System.out.println("Menu Actions:");
        System.out.println("\t(quit) => Exit program");
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
