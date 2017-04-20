package Scrummer;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Main {

    public static void main(String[] args) {
        boolean isRunning = ActionDispatcher.enable();

        while (isRunning) {
            String action = Menu.expose();
            if (action.equals("quit")) {
                isRunning = false;
            } else {
                ActionDispatcher.dispatch(action);
            }
        }

        ActionDispatcher.disable();
    }
}
