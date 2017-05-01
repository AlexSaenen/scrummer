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

    public int getBacklogId(String projectName) {
        return getBacklogIdQuery(projectName);
    }

    public int plan(String title, int duration, String[] projectInfo) {
        UserInput inputMethod = new UserInput();

//        UserStory.moveAllToProject(projectInfo[1]);

        Supplier<List<Integer>> selector = ()-> {
            List<Integer> selected = new ArrayList<>();
            UserStory.getToDos(Integer.valueOf(projectInfo[0]));

            String input = inputMethod.getString("[n,n,...] Make a list of selections: ");
            String[] stringSelections = input.split(",");

            for (String select : stringSelections) {
                selected.add(Integer.valueOf(select));
            }

            return selected;
        };

        List<Integer> selectedStories = selector.get();

        // for each selected, move to sprint backlog

        // if fails, rollback

        int result = planQuery(title, duration, projectInfo[1]);

        if (result != -1) {
            apply();
        }

        return result;
    }
//    public int start(String projectName) {
//        return startQuery(projectName);
//    }
}
