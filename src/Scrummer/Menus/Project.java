package Scrummer.Menus;

import Scrummer.Menu;

/**
 * Created by alexsaenen on 4/24/17.
 */
public class Project extends Menu {


    public Project() {
        userAllowedRequests = new String[] {"createStory", "moveStory", "allStories", "deselect"};
    }

    @Override
    protected void display() {
        System.out.println("\n========================");
        System.out.println("\nProject Actions:");
        System.out.println("\t(deselect) => Deselect Project and return to previous menu");
        System.out.println("\nUserStory Actions:");
        System.out.println("\t(createStory) => Add new User Story");
        System.out.println("\t(moveStory) => Move a User Story to the Sprint Backlog");
        System.out.println("\t(allStories) => View all User Stories");
        System.out.println();
    }
}
