package Scrummer.Controllers;

import Scrummer.ActionHandlers.Sprint;
import Scrummer.ActionHandlers.UserStory;
import Scrummer.ORMS.SprintORM;
import Scrummer.UserInput;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class Sprints extends SprintORM {

    public int create(String projectName, int backlogId) {
        return createQuery(projectName, backlogId);
    }

    public int getCurrentBacklogId(String projectName) {
        return getCurrentBacklogIdQuery(projectName);
    }

    public int plan(String title, int duration, String[] projectInfo) {
        UserInput inputMethod = new UserInput();

        int sprintId = getCurrentBacklogId(projectInfo[1]);

        if (sprintId == -1) {
            return -1;
        }

        if (UserStory.moveAll(sprintId, Integer.valueOf(projectInfo[0])) == false) {
            System.err.println("Failed to move all sprint backlog stories to project's backlog");
            return -1;
        }

        Supplier<List<Integer>> selector = ()-> {
            List<Integer> selected = new ArrayList<>();
            UserStory.getToDos(Integer.valueOf(projectInfo[0]));

            String input = inputMethod.getString("[n,n,...] Make a list of selections: ");
            String[] stringSelections = input.split(",");

            try {
                for (String select : stringSelections) {
                    selected.add(Integer.valueOf(select));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Please format properly your selection list");
                return null;
            }

            return selected;
        };


        List<Integer> selectedStories;
        do {
            selectedStories = selector.get();
        } while (selectedStories == null);

        if (UserStory.moveSome(Integer.valueOf(projectInfo[0]), sprintId, selectedStories.toArray(new Integer[selectedStories.size()])) == false) {
            cancel();
            return -1;
        }

        int result = planQuery(title, duration, projectInfo[1]);

        if (result != -1) {
            apply();
        }

        return result;
    }

    public int start(String projectName) {
        int queryStatus = startQuery(projectName);
        if (queryStatus == 0) {
            System.out.println("Plan the project first");
        }
        return queryStatus;
    }
}
