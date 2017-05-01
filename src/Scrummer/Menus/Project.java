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
            "planSprint", "startSprint"};
    }

    @Override
    protected void display() {
        System.out.println("\n========"+selectedProject+"========");
        System.out.println("\nProject Actions:");
        System.out.println("\t(deselect) => Deselect Project and return to previous menu");
        System.out.println("\nUserStory Actions:");
        System.out.println("\t(createStory) => Add new User Story");
        System.out.println("\t(moveStory) => Move a User Story to the Sprint Backlog");
        System.out.println("\t(userStory) => Get information about a User Story");
        System.out.println("\t(allStories) => View all User Stories");
        System.out.println("\t(updateStoryStatus) => Update the Status of one Story");
        System.out.println("\nSprint Actions:");
        System.out.println("\t(planSprint) => Plan the sprint duration and title");
        System.out.println("\t(startSprint) => Start the sprint for this project");
        System.out.println();
    }
}
