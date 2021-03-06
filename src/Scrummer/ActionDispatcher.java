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
        handlers = new ActionHandler[] {new Project(), new UserStory(), new Sprint(), new MenuNavigator(), new Engineer(), new Team()};
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

    static public void dispatch(String action, String[] params) {
        ActionHandler handler = matchActionHandler(action);
        if (handler != null) {
            handler.handle(action, params);
        } else {
            System.err.println("Unknown action, please select a desired action proposed between ()");
        }
    }

    static public void disable() {
        for (int i = 0; i < handlers.length; i++) {
            handlers[i].disable();
        }
    }

    static public ActionHandler findHandler(String handlerName) {
        for (int i = 0; i < handlers.length; i++) {
            if (handlers[i].getClass().toString().equals(handlerName)) {
                return handlers[i];
            }
        }

        return null;
    }

}
