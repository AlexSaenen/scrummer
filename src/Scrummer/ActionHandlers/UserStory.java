package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.UserStories;
import Scrummer.UserInput;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStory extends ActionHandler {

    private static UserStories userStoriesController = new UserStories();
    static private UserInput user = new UserInput();

    @Override
    protected void enableActions() {
        actions = new String[]{"createStory"};
    }

    @Override
    protected boolean isEnabled() {
        return userStoriesController.isPrepared;
    }

    @Override
    protected void disable() {
        userStoriesController.finish();
    }

    @SuppressWarnings("unused")
    static public void createStory(String[] params) {
        String role = user.getString("Role: ");
        String goal = user.getString("Goal: ");
        String reason = user.getString("Reason: ");

        int priority = -1;

        while (priority < 0 || priority > 10) {
            priority = user.getInt("Priority: ");
        }

        String aClass = user.getString("class: ");
        int backlogId = Integer.valueOf(params[0]);

        userStoriesController.create(role, goal, reason, priority, aClass, backlogId);
    }
}
