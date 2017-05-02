package Scrummer.Menus;

import Scrummer.Menu;


/**
 * Created by alexsaenen on 3/23/17.
 */
public class Primary extends Menu {

    public Primary() {
        userAllowedRequests = new String[] {
                "quit", "selectProject",
                "allProjects", "addProject", "project",
                "engineer"
        };
    }

    protected void display() {
        System.out.println("\n========================");
        System.out.println("Menu Actions:");
        System.out.println("\t(quit) => Exit program");
        System.out.println("\nProject Actions:");
        System.out.println("\t(addProject) => Add new project");
        System.out.println("\t(allProjects) => Get details about all projects");
        System.out.println("\t(project) => Get details about a project");
        System.out.println("\t(selectProject) => Get more actions with a project");
        System.out.println("\nEngineer Actions:");
        System.out.println("\t(engineer) => Get details about an engineer");
        System.out.println();
    }
}
