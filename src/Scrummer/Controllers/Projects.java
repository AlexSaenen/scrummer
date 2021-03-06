package Scrummer.Controllers;

import Scrummer.ActionHandlers.Backlog;
import Scrummer.ActionHandlers.Sprint;
import Scrummer.ActionHandlers.Team;
import Scrummer.ActionHandlers.UserStory;
import Scrummer.ORMS.ProjectORM;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Projects extends ProjectORM {

    private void displayProjectInfo(ResultSet project) throws SQLException {
        System.out.print("Name: " + project.getString(1));
        System.out.print(", Due Date: " + project.getDate(2));
        System.out.print(", Created on: " + project.getDate(3));
        System.out.println(", Description: " + project.getString(4));
    }

    public boolean add(String projectName, Date dueDate, Date creationDate, String description) {
        int backlogId = Backlog.create(false);

        if (backlogId == -1) {
            cancel();
            return false;
        }

        if (addQuery(projectName, dueDate, creationDate, description, backlogId) == -1) {
            cancel();
            return false;
        }

        backlogId = Backlog.create(true);

        if (backlogId == -1) {
            cancel();
            return false;
        }

        if (Sprint.create(projectName, backlogId) == -1) {
            cancel();
            return false;
        }

        apply();

        return true;
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
                System.out.println("\n==PROJECT BACKLOG==");
                UserStory.allStories(new String[]{String.valueOf(projects.getInt(5)), projectName});
                int sprintBacklogId = Sprint.getBacklogId(projectName);
                if (sprintBacklogId == -1) {
                    System.err.println("Had trouble finding sprint info for this project");
                    return false;
                }

                System.out.println("==SPRINT BACKLOG==");
                UserStory.allStories(new String[]{String.valueOf(sprintBacklogId), projectName});
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }
}
