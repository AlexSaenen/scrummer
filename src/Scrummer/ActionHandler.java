package Scrummer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by alexsaenen on 3/23/17.
 */
public abstract class ActionHandler {

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

    protected abstract void enableActions();
    protected abstract boolean isEnabled();
    protected abstract void disable();
}
