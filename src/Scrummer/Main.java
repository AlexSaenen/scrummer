package Scrummer;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Main {

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            String action = Menu.expose();
            if (action.equals("quit")) {
                isRunning = false;
            } else {
                ActionDispatcher.dispatch(action);
            }
        }

        ActionDispatcher.disable();

//        meetingsController.moveBookings("Alternate room", "Office room", new Date(Calendar.getInstance().getTimeInMillis()));
//        teamsController.finish();
//        meetingsController.finish();
//        roomsController.finish();
    }
}
