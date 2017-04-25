package Scrummer;

import Scrummer.Menus.Primary;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Main {

    static private boolean isRunning = false;

    public static void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        Menu primaryMenu = new Primary();
        isRunning = ActionDispatcher.enable();

        while (isRunning) {
            String action = primaryMenu.expose();
            ActionDispatcher.dispatch(action);
        }

        ActionDispatcher.disable();
    }
}
