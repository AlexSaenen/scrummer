package Scrummer.Controllers;

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
}
