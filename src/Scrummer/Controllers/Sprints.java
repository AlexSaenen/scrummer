package Scrummer.Controllers;

import Scrummer.ActionHandlers.Sprint;
import Scrummer.ORMS.SprintORM;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Sprints extends SprintORM {

    public int create(String projectName, int backlogId) {
        return createQuery(projectName, backlogId);
    }

    public int getBacklogId(String projectName) {
        return getBacklogIdQuery(projectName);
    }

    public int plan(String title, int duration, String projectName) {
        int result = planQuery(title, duration, projectName);

        if (result != -1) {
            apply();
        }

        return result;
    }
//    public int start(String projectName) {
//        return startQuery(projectName);
//    }
}
