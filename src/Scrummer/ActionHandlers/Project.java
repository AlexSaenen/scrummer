package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Projects;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Project extends ActionHandler {

    static private Projects projectsController = new Projects();

    protected void enableActions() {
        actions = new String[]{"allProjects", "addProject", "project", "returnProject"};
    }

    protected boolean isEnabled() {
        return projectsController.isPrepared;
    }

    protected void disable() {
        projectsController.finish();
    }


    @SuppressWarnings("unused")
    static public void allProjects() {
        projectsController.getAll();
    }

    @SuppressWarnings("unused")
    static public void addProject() {
        String projectName = user.getString("SelectProject Name: ");
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
        Date dueDate;

        do {
            dueDate = user.getDate("Due Date: ", true);
            if (dueDate.before(currentDate)) {
                System.err.println("Expecting a future date");
            }
        } while (dueDate.before(currentDate));

        String description = user.getString("Description: ");

        projectsController.add(projectName, dueDate, currentDate, description);
    }

    @SuppressWarnings("unused")
    static public void project() {
        String projectName = user.getString("SelectProject Name: ");
        System.out.println();
        if (projectsController.get(projectName)) {
            // Details of user stories to do
        }
    }

    @SuppressWarnings("unused")
    static public ResultSet returnProject(String projectName) {
        return projectsController.getQuery(projectName);
    }
}