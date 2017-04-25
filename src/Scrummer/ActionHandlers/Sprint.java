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
        actions = new String[]{"addSprint"};
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
}
