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
        actions = new String[]{"createStory", "moveStory", "allStories"};
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
    static public void allStories(String[] params) {
        userStoriesController.getAll(Integer.valueOf(params[0]));
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

        String aClass = user.getString("Class: ");
        int backlogId = Integer.valueOf(params[0]);

        userStoriesController.create(role, goal, reason, priority, aClass, backlogId);
    }

    @SuppressWarnings("unused")
    static public void moveStory(String[] params) {
        int storyId = -1;

        while (storyId < 0) {
            storyId = user.getInt("User Story Id: ");
        }

        userStoriesController.move(storyId, params[1], Integer.valueOf(params[0]));
    }
}
