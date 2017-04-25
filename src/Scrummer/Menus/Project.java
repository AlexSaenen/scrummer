package Scrummer.Menus;

import Scrummer.Menu;

/**
 * Created by alexsaenen on 4/24/17.
 */
public class Project extends Menu {


    public Project() {
        userAllowedRequests = new String[] {"createStory", "moveStory", "allStories"};
    }

    @Override
    protected void display() {
        System.out.println("\n========================");
        System.out.println("\nUserStory Actions:");
        System.out.println("\t(createStory) => Add new User Story");
        System.out.println("\t(moveStory) => Move a User Story to the Sprint Backlog");
        System.out.println("\t(allStories) => View all User Stories");
        System.out.println();
    }
}
