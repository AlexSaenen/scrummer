package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.UserStories;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStory extends ActionHandler {

    private static UserStories userStoriesController = new UserStories();

    @Override
    protected void enableActions() {

    }

    @Override
    protected boolean isEnabled() {
        return userStoriesController.isPrepared;
    }

    @Override
    protected void disable() {
        userStoriesController.finish();
    }
}
