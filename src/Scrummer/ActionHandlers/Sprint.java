package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Sprints;

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
        return sprintsController.getBacklogId(projectName);
    }

    @SuppressWarnings("unused")
    static public int planSprint(String []params) {
        String title = user.getString("Sprint goal/title: ");

        int duration = -1;
        while (duration < 21 || duration > 28) {
            duration = user.getInt("(between 21 and 28 days) Sprint duration: ");
        }

        return sprintsController.plan(title, duration, params[1]);
    }

//    @SuppressWarnings("unused")
//    static public int startSprint(String projectName) {
//        return sprintsController.start(projectName);
//    }
}
