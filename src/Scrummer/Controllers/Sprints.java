package Scrummer.Controllers;

import Scrummer.ORMS.SprintORM;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Sprints extends SprintORM {

    public int create(String projectName, int backlogId, String title) {
        return createQuery(projectName, backlogId, title);
    }
}
