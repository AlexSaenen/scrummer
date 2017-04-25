package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Backlogs;

/**
 * Created by alexsaenen on 4/19/17.
 */
public class Backlog extends ActionHandler {
    static private Backlogs backlogsController = new Backlogs();

    @Override
    protected void enableActions() {
        actions = new String[]{};
    }

    @Override
    protected boolean isEnabled() {
        return backlogsController.isPrepared;
    }

    @Override
    protected void disable() {
        backlogsController.finish();
    }

    static public int create(boolean isSprint) {
        return backlogsController.create(isSprint);
    }
}
