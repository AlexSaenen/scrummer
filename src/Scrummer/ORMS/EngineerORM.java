package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 5/1/17.
 */
public class EngineerORM extends ORM {

    protected PreparedStatement addAssignmentStatement;
    protected PreparedStatement addStatement;
    protected PreparedStatement addMemberStatement;

    @Override
    protected void CreateStatements() throws SQLException {
        addAssignmentStatement = link.prepareStatement("insert into UserAssignments (name, task) values (?, ?)");
        addStatement = link.prepareStatement("insert into Engineers (name, phone) values (?, ?)");
        addMemberStatement = link.prepareStatement("insert into TeamMembers (name, project) values (?, ?)");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        addAssignmentStatement.close();
        addStatement.close();
        addMemberStatement.close();
    }

    protected void addAssignmentQuery(String who, int what) {
        try {
            addAssignmentStatement.setString(1, who);
            addAssignmentStatement.setInt(2, what);

            int result = addAssignmentStatement.executeUpdate();

            if (result == -1) {
                cancel();
            } else {
                apply();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            cancel();
        }
    }

    protected void addQuery(String who, String phone) {
        try {
            addStatement.setString(1, who);
            addStatement.setString(2, phone);

            int result = addStatement.executeUpdate();

            if (result == -1) {
                System.err.println("Failed to add Engineer, maybe the name or phone is already taken");
                cancel();
            } else {
                apply();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            cancel();
        }
    }

    protected void addMemberQuery(String who, String what) {
        try {
            addMemberStatement.setString(1, who);
            addMemberStatement.setString(2, what);

            int result = addMemberStatement.executeUpdate();

            if (result == -1) {
                System.err.println("Failed to make Engineer member of the project");
                cancel();
            } else {
                apply();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            cancel();
        }
    }
}
