package Scrummer;

import Scrummer.ActionHandlers.*;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class ActionDispatcher {

    static private ActionHandler[] handlers;

    static private ActionHandler matchActionHandler(String action) {
        for (int i = 0; i < handlers.length; i++) {
            if (handlers[i].canHandle(action)) {
                return handlers[i];
            }
        }

        return null;
    }

    static public boolean enable() {
        handlers = new ActionHandler[] {new Project(), new UserStory()};
        return true;
    }

    static public void dispatch(String action) {
        ActionHandler handler = matchActionHandler(action);
        if (handler != null) {
            handler.handle(action);
        } else {
            System.err.println("Unknown action, please select a desired action proposed between ()");
        }
    }

    static public void disable() {
        for (int i = 0; i < handlers.length; i++) {
            handlers[i].disable();
        }
    }
}
