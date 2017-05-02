package Scrummer.Controllers;

import Scrummer.ORMS.TeamORM;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 5/1/17.
 */
public class Teams extends TeamORM {

    private void displayMemberInfo(ResultSet member) throws SQLException {
        System.out.print("Name: " + member.getString(1));
        System.out.print(", Phone: " + member.getString(2));
        System.out.println();
    }

    public boolean isEngineerOf(String who, String project) {
        try {
            ResultSet engineers = getMemberQuery(who, project);

            if (engineers == null) {
                return false;
            }

            engineers.next();
            return engineers.first();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    public void listMembers(String teamName) {
        try {
            ResultSet engineers = getAllMembersQuery(teamName);

            if (engineers == null) {
                return;
            }
            System.out.print("\nENGINEERS:");
            if (!engineers.first()) {
                System.out.println(" None");
            } else {
                System.out.println();
                do {
                    System.out.print("\t");
                    displayMemberInfo(engineers);
                } while (engineers.next());
            }

            System.out.println();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

}
