package Scrummer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class UserInput {

    private BufferedReader bufferedReader;

    public UserInput() {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(streamReader);
    }

    public String getString(String request) {
        System.out.print(request);

        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public int getInt(String request) {
        while (true) {
            String integerString = getString(request);
            try {
                return Integer.parseInt(integerString);
            } catch (NumberFormatException ex) {
                System.err.println("Expecting an integer");
            }
        }
    }

    public Date getDate(String request, boolean retry) {
        Date enteredDate = null;
        StringBuilder builder = new StringBuilder(request);
        builder.insert(0, "(yyyy-mm-dd) ");

        while (enteredDate == null) {
            String dateString = getString(builder.toString());
            try {
                enteredDate = Date.valueOf(dateString);
            } catch (IllegalArgumentException ex) {
                System.err.println("Expecting a date formatted like yyyy-mm-dd");
                enteredDate = null;
            }

            if (retry == false) {
                return enteredDate;
            }
        }

        return enteredDate;
    }
}
