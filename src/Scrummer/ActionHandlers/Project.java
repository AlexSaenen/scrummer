package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Projects;
import Scrummer.UserInput;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Project extends ActionHandler {

    static private Projects projectsController = new Projects();
    static private UserInput user = new UserInput();

    protected void enableActions() {
        actions = new String[]{"allProjects", "addProject", "project"};
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
        String projectName = user.getString("Project Name: ");
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
        String projectName = user.getString("Project Name: ");
        System.out.println();
        if (projectsController.get(projectName)) {

        }
    }
}