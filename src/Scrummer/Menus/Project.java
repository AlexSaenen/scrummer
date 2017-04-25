package Scrummer.Menus;

import Scrummer.Menu;

/**
 * Created by alexsaenen on 4/24/17.
 */
public class Project extends Menu {


    public Project() {
        userAllowedRequests = new String[] {"createStory"};
    }

    @Override
    protected void display() {
        System.out.println("\n========================");
        System.out.println("\nUserStory Actions:");
        System.out.println("\t(createStory) => Add new User Story");
        System.out.println();
    }
}
