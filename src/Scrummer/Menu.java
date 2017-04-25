package Scrummer;

import java.util.Arrays;

/**
 * Created by alexsaenen on 4/24/17.
 */
public abstract class Menu {
    static protected UserInput user = new UserInput();
    protected String[] userAllowedRequests;

    protected abstract void display();

    protected boolean isAllowed(String input) {
        return Arrays.asList(userAllowedRequests).contains(input);
    }
    protected String getInput() {
        return user.getString("What is your request ?: ");
    }

    public String expose() {
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
