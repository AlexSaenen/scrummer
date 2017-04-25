package Scrummer.Controllers;

import Scrummer.ORMS.UserStoryORM;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStories extends UserStoryORM {

    public void create(String role, String goal, String reason, int priority, String aClass, int backlogId) {
        if (createQuery(role, goal, reason, priority, aClass, backlogId) != -1) {
            System.out.println("User Story created");
        }
    }
}
