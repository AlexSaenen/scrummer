package Scrummer.Controllers;

import Scrummer.ActionHandlers.Sprint;
import Scrummer.ORMS.UserStoryORM;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStories extends UserStoryORM {

    private void displayUserStories(ResultSet stories) throws SQLException {
        System.out.print("UserStory ID:  " + stories.getInt(1));
        System.out.println(", Goal : " + stories.getString(4));
    }


    private void displayUserStoryInfo(ResultSet userStories) throws SQLException{
        System.out.print("As a " + userStories.getString(3));
        System.out.print(", I want " + userStories.getString(4));
        System.out.println(" so that " + userStories.getString(5));
    }

    public void create(String role, String goal, String reason, int priority, String aClass, int backlogId) {
        if (createQuery(role, goal, reason, priority, aClass, backlogId) != -1) {
            System.out.println("User Story created");
        }
    }

    public boolean move(int storyId, String projectName, int backlogId) {
        if (getBacklogIdQuery(storyId) != backlogId) {
            System.err.println("This User Story is not in the Project's Backlog");
        }

        int sprintId = Sprint.getBacklogId(projectName);

        if (sprintId != -1) {
            if (updateBacklogIdQuery(storyId, sprintId) == -1) {
                System.err.println("Failed to move User Story to Sprint Backlog");
            }
        }

        return false;
    }

    public void getAll(int backlogId) {
        ResultSet stories = getAllQuery(backlogId);
        try {
            System.out.print("\nUSER STORIES:");
            if (!stories.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayUserStories(stories);
                } while (stories.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean get(int userStoryId) {
        ResultSet userStories = getQuery(userStoryId);
        try {
            userStories.next();
            if (!userStories.first()) {
                System.out.println(" None");
            } else {
                displayUserStoryInfo(userStories);
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    public void changeStatus(int userStoryId, int status) {
        changeStatusQuery(userStoryId, status);
    }

    public void getTodos(int backlogId) {
        ResultSet stories = getToDoQuery(backlogId);
        try {
            System.out.print("\nUSER STORIES:");
            if (!stories.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayUserStories(stories);
                } while (stories.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
