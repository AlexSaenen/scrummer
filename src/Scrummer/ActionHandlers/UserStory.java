package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.UserStories;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStory extends ActionHandler {

    private static UserStories userStoriesController = new UserStories();

    @Override
    protected void enableActions() {
        actions = new String[]{"createStory", "moveStory", "allStories", "userStory", "sprintRecap", "deleteStory"};
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
    static public void userStory(String[] params) {
        int id = user.getInt("User Story ID: ");
        userStoriesController.get(id);
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
    static public void deleteStory(String[] params) {
        int storyId = -1;

        while (storyId < 0) {
            storyId = user.getInt("Id of User Story: ");
        }

        int backlogId = Integer.valueOf(params[0]);

        userStoriesController.remove(storyId, backlogId);
    }

    @SuppressWarnings("unused")
    static public void moveStory(String[] params) {
        int storyId = -1;

        while (storyId < 0) {
            storyId = user.getInt("User Story Id: ");
        }

        userStoriesController.move(storyId, params[1], Integer.valueOf(params[0]));
    }

    static public void displayInfo(ResultSet story) throws SQLException {
        userStoriesController.displayUserStoryInfo(story);
    }

    static public boolean moveAll(int from, int to) {
        return userStoriesController.moveAll(from, to);
    }

    static public boolean moveSome(int from, int to, Integer[] which) {
        return userStoriesController.moveSome(from, to, which);
    }

    static public void getToDos(Integer backlogId, boolean forSprint) {
        userStoriesController.getTodos(backlogId, forSprint);
    }

    static public void getBandD(Integer backlogId) {
        userStoriesController.getBandD(backlogId);
    }

    static public void getTesting(Integer backlogId) {
        userStoriesController.getTesting(backlogId);
    }

    static public void getCompleted(Integer backlogId) {
        userStoriesController.getCompleted(backlogId);
    }

    static public void sprintRecap(String[] params) {
        int backlogId = Sprint.getBacklogId(params[1]);
        System.out.print("Current Sprint " + Sprint.getEndDate(backlogId) + " :\n\tTo Do:");
        getToDos(backlogId, true);
        System.out.print("\tBuild And Document:");
        getBandD(backlogId);
        System.out.print("\tTesting:");
        getTesting(backlogId);
        System.out.print("\tCompleted:");
        getCompleted(backlogId);
    }

    static public boolean isInSprint(Integer storyId, String projectName) {
        return userStoriesController.isInSprint(storyId, projectName);
    }

    static public boolean isCompleted(Integer storyId) {
        return userStoriesController.isCompleted(storyId);
    }

    @SuppressWarnings("unused")
    static public void updateStoryStatus(String[] params) {
        int storyId = -1;
        int status = -1;

        while (storyId < 0) {
            storyId = user.getInt("User Story Id: ");

        }
        while (status < 0) {
            status = user.getInt("new User Story status : Todo (1)" +
                    "\n\tBuild-and-document (2)" +
                    "\n\tTesting (3)" +
                    "\n\t" +
                    "Completed (4) :");

        }

        userStoriesController.changeStatus(storyId, status);
    }
}
