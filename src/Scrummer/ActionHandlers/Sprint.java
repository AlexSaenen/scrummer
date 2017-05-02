package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Sprints;

import java.sql.Date;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Sprint extends ActionHandler {

    static private Sprints sprintsController = new Sprints();

    @Override
    protected void enableActions() {
        actions = new String[]{"startSprint", "planSprint"};
    }

    @Override
    protected boolean isEnabled() {
        return sprintsController.isPrepared;
    }

    @Override
    protected void disable() {
        sprintsController.finish();
    }

    static public int create(String projectName, int backlogId) {
        return sprintsController.create(projectName, backlogId);
    }

    static public int getBacklogId(String projectName) {
        return sprintsController.getCurrentBacklogId(projectName);
    }

    static public Date getEndDate(int backlogId) {
        return sprintsController.getEndDate(backlogId);
    }

    @SuppressWarnings("unused")
    static public int planSprint(String []params) {
        String title = user.getString("Sprint goal/title: ");

        int duration = -1;
        while (duration < 21 || duration > 28) {
            duration = user.getInt("(between 21 and 28 days) Sprint duration: ");
        }

        return sprintsController.plan(title, duration, params);
    }

    @SuppressWarnings("unused")
    static public int startSprint(String []params) {
        return sprintsController.start(params[1]);
    }
}
