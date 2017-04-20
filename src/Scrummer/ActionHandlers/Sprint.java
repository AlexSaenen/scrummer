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

    }

    @Override
    protected boolean isEnabled() {
        return sprintsController.isPrepared;
    }

    @Override
    protected void disable() {

    }
}
