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

    private void displayUserStoriesForSprint(ResultSet stories) throws SQLException {
        System.out.println("\t\t - " + stories.getString(4));
    }

    public void displayUserStoryInfo(ResultSet userStories) throws SQLException{
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
            return false;
        }

        int sprintId = Sprint.getBacklogId(projectName);

        if (sprintId != -1) {
            if (updateBacklogIdQuery(storyId, sprintId, -1) == -1) {
                System.err.println("Failed to move User Story to Sprint Backlog");
                return false;
            }

            return true;
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

    public boolean moveAll(int from ,int to) {
        if (updateAllBacklogIdQuery(from, to) == -1) {
            System.err.println("Failed to move User Stories to another Backlog");
            return false;
        }

        return true;
    }

    public boolean moveSome(int from ,int to, Integer[] some) {
        if (some.length == 0) {
            return false;
        }

        for (Integer who : some) {
            if (updateBacklogIdQuery(who, to, from) == -1) {
                System.err.println("Failed to move User Story to Sprint Backlog");
                return false;
            }
        }

        return true;
    }

    public void changeStatus(int userStoryId, int status) {
        changeStatusQuery(userStoryId, status);
    }

    public boolean isInSprint(int userStoryId, String projectName) {
        int sprintId = Sprint.getBacklogId(projectName);

        if (sprintId == -1) {
            return false;
        }

        return getBacklogIdQuery(userStoryId) == sprintId;
    }

    public boolean isCompleted(int userStoryId) {
        ResultSet userStories = getQuery(userStoryId);
        try {
            userStories.next();
            if (!userStories.first()) {
                return true;
            } else {
                return userStories.getInt(2) == 3;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return true;
    }

    public void getTodos(int backlogId, boolean forSprint) {
        ResultSet stories = getToDoQuery(backlogId);
        try {
            if (!forSprint)
                System.out.print("\nUSER STORIES:");
            if (!stories.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    if (!forSprint)
                        displayUserStories(stories);
                    else
                        displayUserStoriesForSprint(stories);
                } while (stories.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void getBandD(int backlogId) {
        ResultSet stories = getBandDQuery(backlogId);
        try {
            if (!stories.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayUserStoriesForSprint(stories);
                } while (stories.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }



    public void getTesting(int backlogId) {
        ResultSet stories = getTestingQuery(backlogId);
        try {
            if (!stories.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayUserStoriesForSprint(stories);
                } while (stories.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }



    public void getCompleted(int backlogId) {
        ResultSet stories = getCompletedQuery(backlogId);
        try {
            if (!stories.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayUserStoriesForSprint(stories);
                } while (stories.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void remove(int storyId, int backlogId) {
        if (removeQuery(storyId, backlogId) != -1) {
            System.out.println("User Story removed");
        }
    }
}
