package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Teams;
import Scrummer.UserInput;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Team extends ActionHandler {

    static private Teams teamsController = new Teams();
    static private UserInput user = new UserInput();

    protected void enableActions() {
        actions = new String[] {"allTeams", "team", "addTeam"};
    }
    protected boolean isEnabled() {
        return teamsController.isPrepared;
    }
    protected void disable() {
        teamsController.finish();
    }

    @SuppressWarnings("unused")
    static public void allTeams() {
        teamsController.getAll();
    }

    @SuppressWarnings("unused")
    static public void team() {
        String teamName = user.getString("Team Name: ");
        Date from = user.getDate("After (included): ", true);
        Date to = user.getDate("Before (included): ", true);
        System.out.println();
        if (teamsController.get(teamName)) {
            Meeting.getTeamMeetingsBetween(teamName, from, to);
        }
    }

    @SuppressWarnings("unused")
    static public void addTeam() {
        String teamName = user.getString("Team Name: ");
        String leaderName = user.getString("Leader Name: ");
        Date creationDate = new Date(Calendar.getInstance().getTimeInMillis());
        String projectName = user.getString("Project Name: ");
        teamsController.add(teamName, leaderName, creationDate, projectName);
    }
}
