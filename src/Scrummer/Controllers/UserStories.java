package Scrummer.Controllers;

import Scrummer.ORMS.UserStoryORM;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStories extends UserStoryORM {

    private void displayUserStories(ResultSet stories) throws SQLException {
        System.out.print("UserStory ID:  " + stories.getString(1));
        System.out.println(", Goal : " + stories.getString(4));
    }

    public void create(String role, String goal, String reason, int priority, String aClass, int backlogId) {
        if (createQuery(role, goal, reason, priority, aClass, backlogId) != -1) {
            System.out.println("User Story created");
        }
    }

    public void getAll() {
        ResultSet stories = getAllQuery();
        try {
            System.out.print("\nUSER STORIES:");
            if (!stories.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayUserStories(stories);
                } while (stories.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        close();
    }
}
