package Scrummer.ActionHandlers;

import Scrummer.ActionDispatcher;
import Scrummer.ActionHandler;
import Scrummer.Main;
import Scrummer.Menu;
import Scrummer.Menus.SelectProject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 4/24/17.
 */
public class MenuNavigator extends ActionHandler {
    @Override
    protected void enableActions() {
        actions = new String[]{"quit", "selectProject"};
    }

    @Override
    protected boolean isEnabled() {
        return true;
    }

    @Override
    protected void disable() {

    }

    @SuppressWarnings("unused")
    static public void quit() {
        Main.stop();
    }

    @SuppressWarnings("unused")
    static public void selectProject() {
        Menu projectMenu = new SelectProject();
        String project = projectMenu.expose();
        Scrummer.ActionHandlers.Project handler = (Scrummer.ActionHandlers.Project) ActionDispatcher.findHandler("SelectProject");

        ResultSet projectResult = handler.returnProject(project);

        try {
            if (projectResult.next()) {
                Menu primaryProjectMenu = new Scrummer.Menus.Project();
                String action = primaryProjectMenu.expose();
                ActionDispatcher.dispatch(action, new String[] {String.valueOf(projectResult.getInt(5)), project});
            } else {
                System.out.println("No such project");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
