package Scrummer.Controllers;

import Scrummer.ActionHandlers.Backlog;
import Scrummer.ActionHandlers.Sprint;
import Scrummer.ORMS.ProjectORM;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Projects extends ProjectORM {

    private void displayProjectInfo(ResultSet project) throws SQLException {
        System.out.print("SelectProject Name: " + project.getString(1));
        System.out.print(", Due Date: " + project.getDate(2));
        System.out.print(", Created on: " + project.getDate(3));
        System.out.println(", Description: " + project.getString(4));
    }

    public boolean add(String projectName, Date dueDate, Date creationDate, String description) {
        try {
            Savepoint save = link.setSavepoint();
            int backlogId = Backlog.create(false);

            if (backlogId == -1) {
                System.out.println("Cancel backlog");
                cancel(save);
                return false;
            }
            apply();

            if (addQuery(projectName, dueDate, creationDate, description, backlogId) == -1) {
                System.out.println("Cancel add");
                cancel(save);
                return false;
            }
            apply();

            backlogId = Backlog.create(true);

            if (backlogId == -1) {
                System.out.println("Cancel create");
                cancel(save);
                return false;
            }
            apply();

            if (Sprint.create(projectName, backlogId) == -1) {
                System.out.println("Cancel create sprint");
                cancel(save);
                return false;
            }

            apply();

            return true;
        } catch (SQLException ex) {
            System.err.println("Failed setting a save point");
            return false;
        }
    }

    public void getAll() {
        ResultSet projects = getAllQuery();
        try {
            System.out.print("\nPROJECTS:");
            if (!projects.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayProjectInfo(projects);
                } while (projects.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        close();
    }

    public boolean get(String projectName) {
        ResultSet projects = getQuery(projectName);
        try {
            projects.next();
            if (!projects.first()) {
                System.out.println(" None");
            } else {
                displayProjectInfo(projects);
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }
}
