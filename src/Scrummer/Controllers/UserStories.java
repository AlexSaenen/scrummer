package Scrummer.Controllers;

import Scrummer.ActionHandlers.Project;
import Scrummer.ActionHandlers.Sprint;
import Scrummer.ORMS.UserStoryORM;

import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStories extends UserStoryORM {

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
}
