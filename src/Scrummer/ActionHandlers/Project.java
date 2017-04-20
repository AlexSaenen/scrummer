package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Projects;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Project extends ActionHandler {

    static private Projects projectsController = new Projects();

    @Override
    protected void enableActions() {

    }

    @Override
    protected boolean isEnabled() {
        return projectsController.isPrepared;
    }

    @Override
    protected void disable() {
        projectsController.finish();
    }
}
