package Scrummer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by alexsaenen on 3/23/17.
 */
public abstract class ActionHandler {

    static protected UserInput user = new UserInput();
    protected String[] actions;

    public ActionHandler() {
        enableActions();
    }

    public boolean canHandle(String action) {
        return Arrays.asList(actions).contains(action);
    }

    public void handle(String action) {
        Method responder = null;
        try {
            responder = this.getClass().getMethod(action);
        } catch (NoSuchMethodException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            responder.invoke(this);
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InvocationTargetException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void handle(String action, String[] params) {
        Method responder = null;
        try {
            responder = this.getClass().getMethod(action, new Class[]{String[].class});
        } catch (NoSuchMethodException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            responder.invoke(this, new Object[]{params});
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InvocationTargetException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected abstract void enableActions();
    protected abstract boolean isEnabled();
    protected abstract void disable();
}
