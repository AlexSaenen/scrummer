package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Engineers;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by alexsaenen on 5/1/17.
 */
public class Engineer extends ActionHandler {

    static private Engineers engineersController = new Engineers();

    @Override
    protected void enableActions() {
        actions = new String[]{"assignEngineer", "addEngineer", "makeMember"};
    }

    @Override
    protected boolean isEnabled() {
        return engineersController.isPrepared;
    }

    @Override
    protected void disable() {
        engineersController.finish();
    }

    @SuppressWarnings("unused")
    static public void assignEngineer(String[] params) {
        String engineerName = user.getString("Engineer's full name: ");

        Integer storyId;

        do {
            storyId = user.getInt("Id of User Story to assign: ");
        } while (storyId < 0);

        engineersController.assign(engineerName, storyId, params[1]);
    }

    @SuppressWarnings("unused")
    static public void addEngineer(String[] params) {
        String engineerName = user.getString("Engineer's full name: ");
        String phone = user.getString("Engineer's phone: ");
        engineersController.add(engineerName, phone);
    }

    @SuppressWarnings("unused")
    static public void makeMember(String[] params) {
        String engineerName = user.getString("Engineer's full name: ");
        engineersController.makeMember(engineerName, params[1]);
    }
}