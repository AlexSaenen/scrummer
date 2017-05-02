package Scrummer.Menus;

import Scrummer.Menu;

/**
 * Created by alexsaenen on 4/24/17.
 */
public class Project extends Menu {

    private String selectedProject;

    public Project(String project) {
        selectedProject = project;
        userAllowedRequests = new String[] {"createStory", "moveStory", "allStories", "userStory", "updateStoryStatus", "deselect",
            "planSprint", "startSprint", "assignEngineer", "addEngineer", "makeMember", "sprintEngineers"};
    }

    @Override
    protected void display() {
        user.getString("\n\nPress enter to continue ...");
        System.out.println("\n========"+selectedProject+"========");
        System.out.println("\nProject Actions:");
        System.out.println("\t(deselect) => Deselect Project and return to previous menu");
        System.out.println("\nEngineer Actions:");
        System.out.println("\t(assignEngineer) => Assign an Engineer to a UserStory");
        System.out.println("\t(addEngineer) => Add a new Engineer");
        System.out.println("\t(makeMember) => Make Engineer member of a Project");
        System.out.println("\nUserStory Actions:");
        System.out.println("\t(createStory) => Add new User Story");
        System.out.println("\t(moveStory) => Move a User Story to the Sprint Backlog");
        System.out.println("\t(userStory) => Get information about a User Story");
        System.out.println("\t(allStories) => View all User Stories");
        System.out.println("\t(updateStoryStatus) => Update the Status of one Story");
        System.out.println("\nSprint Actions:");
        System.out.println("\t(planSprint) => Plan the sprint duration and title");
        System.out.println("\t(startSprint) => Start the sprint for this project");
        System.out.println("\t(sprintEngineers) => Display information about all the sprint's engineers");
        System.out.println();
    }
}
