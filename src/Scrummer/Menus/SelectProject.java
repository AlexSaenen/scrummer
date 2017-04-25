package Scrummer.Menus;

import Scrummer.Menu;

/**
 * Created by alexsaenen on 4/24/17.
 */
public class SelectProject extends Menu {

    public SelectProject() {
        userAllowedRequests = new String[] {};
    }

    protected void display() {
        System.out.println("\n========================");
        System.out.println("SelectProject Select:");
    }

    @Override
    protected boolean isAllowed(String input) { return true; }
    @Override
    protected String getInput() { return user.getString("Name of concerned project ?: "); }
}
