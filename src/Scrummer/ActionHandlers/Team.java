package Scrummer.ActionHandlers;

import Scrummer.ActionHandler;
import Scrummer.Controllers.Teams;

/**
 * Created by alexsaenen on 5/1/17.
 */
public class Team extends ActionHandler {

    static protected Teams teamsController = new Teams();

    @Override
    protected void enableActions() {
        actions = new String[]{"sprintEngineers"};
    }

    @Override
    protected boolean isEnabled() {
        return teamsController.isPrepared;
    }

    @Override
    protected void disable() {
        teamsController.finish();
    }

    static public boolean isEngineerOf(String who, String project) {
        return teamsController.isEngineerOf(who, project);
    }

    @SuppressWarnings("unused")
    static public void sprintEngineers(String[] params) {
        teamsController.listMembers(params[1]);
    }

    static public void getProjectsFor(String who) {
        teamsController.getProjectsFor(who);
    }
}
