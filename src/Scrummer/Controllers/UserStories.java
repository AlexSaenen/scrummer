package Scrummer.Controllers;

import Scrummer.ORMS.UserStoryORM;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStories extends UserStoryORM {

    public void add(int status, String role, String goal, String reason, int priority, String aClass, int backlogId) {
        addQuery(status, role, goal, reason, priority, aClass, backlogId);
    }
}
