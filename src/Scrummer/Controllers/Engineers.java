package Scrummer.Controllers;

import Scrummer.ActionHandlers.Team;
import Scrummer.ActionHandlers.UserStory;
import Scrummer.ORMS.EngineerORM;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 5/1/17.
 */
public class Engineers extends EngineerORM {

    public void assign(String who, int what, String projectName) {
        if (UserStory.isCompleted(what)) {
            System.err.println(what + " is completed, cannot assign engineers");
            return;
        }

        if (UserStory.isInSprint(what, projectName) == false) {
            System.err.println(what + " is not the id of a UserStory in the current Sprint");
            return;
        }

        if (Team.isEngineerOf(who, projectName) == false) {
            System.err.println(who + " does not exist or is not part of the sprinter team");
            return;
        }

        addAssignmentQuery(who, what);
    }

    public void dissociate(String who, int what, String projectName) {
        if (UserStory.isInSprint(what, projectName) == false) {
            System.err.println(what + " is not the id of a UserStory in the current Sprint");
            return;
        }

        if (Team.isEngineerOf(who, projectName) == false) {
            System.err.println(who + " does not exist or is not part of the sprinter team");
            return;
        }

        removeAssignmentQuery(who, what);
    }

    public void add(String who, String phone) {
        addQuery(who, phone);
    }

    public void remove(String who) {
        removeQuery(who);
    }

    public void makeMember(String who, String project) {
        addMemberQuery(who, project);
    }

    public void removeMember(String who, String project) {
        removeMemberQuery(who, project);
    }

    public void getStories(String who) {
        try {
            ResultSet stories = getStoriesQuery(who);

            System.out.println("\nUSER STORIES:");
            if (!stories.first()) {
                System.out.println("No stories");
            } else {
                do {
                    System.out.print("\t");
                    UserStory.displayInfo(stories);
                } while (stories.next());
            }

            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public int get(String who) {
        try {
            ResultSet engineers = getQuery(who);

            if (!engineers.first()) {
                System.err.println("No such engineer");
                return -1;
            } else {
                System.out.print("\n\tName: " + engineers.getString(1));
                System.out.print(", Phone: " + engineers.getString(2));
            }

            System.out.println();
            return 1;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -1;
        }
    }

    public void getAll() {
        try {
            ResultSet engineers = getAllQuery();

            if (!engineers.first()) {
                System.err.println("No such engineer");
            } else {
                do {
                    System.out.print("\n\tName: " + engineers.getString(1));
                    System.out.print(", Phone: " + engineers.getString(2));
                } while (engineers.next());
            }

            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        close();
    }

}