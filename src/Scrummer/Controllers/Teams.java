package Scrummer.Controllers;

import Scrummer.ORMS.TeamORM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Created by alexsaenen on 3/23/17.
 */
public class Teams extends TeamORM {

    private void displayTeamInfo(ResultSet team) throws SQLException {
        System.out.print("Team Name: " + team.getString(1));
        System.out.print(", Team Leader: " + team.getString(2));
        System.out.print(", Formed on: " + team.getDate(3));
        System.out.println(", Project Name: " + team.getString(4));
    }

    public void getAll() {
        ResultSet teams = getAllQuery();
        try {
            System.out.print("\nTEAMS:");
            if (!teams.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayTeamInfo(teams);
                } while (teams.next());
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        close();
    }

    public boolean get(String teamName) {
        ResultSet teams = getQuery(teamName);
        try {
            teams.next();
            if (!teams.first()) {
                System.out.println(" None");
            } else {
                displayTeamInfo(teams);
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    public boolean add(String teamName, String leader, Date date, String projectName) {
        return addQuery(teamName, leader, date, projectName) != -1;
    }

}
