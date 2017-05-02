package Scrummer.Controllers;

import Scrummer.ActionHandlers.Team;
import Scrummer.ActionHandlers.UserStory;
import Scrummer.ORMS.EngineerORM;

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

    public void add(String who, String phone) {
        addQuery(who, phone);
    }

    public void makeMember(String who, String project) {
        addMemberQuery(who, project);
    }
}
