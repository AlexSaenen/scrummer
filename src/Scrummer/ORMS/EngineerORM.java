package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 5/1/17.
 */
public class EngineerORM extends ORM {

    protected PreparedStatement addAssignmentStatement;
    protected PreparedStatement removeAssignmentStatement;
    protected PreparedStatement addStatement;
    protected PreparedStatement removeStatement;
    protected PreparedStatement addMemberStatement;
    protected PreparedStatement removeMemberStatement;
    protected PreparedStatement getStatement;
    protected PreparedStatement getStoriesStatement;

    @Override
    protected void CreateStatements() throws SQLException {
        addAssignmentStatement = link.prepareStatement("insert into UserAssignments (name, task) values (?, ?)");
        removeAssignmentStatement = link.prepareStatement("delete from UserAssignments where name = ? and task = ?");
        addStatement = link.prepareStatement("insert into Engineers (name, phone) values (?, ?)");
        removeStatement = link.prepareStatement("delete from Engineers where name = ?");
        addMemberStatement = link.prepareStatement("insert into TeamMembers (name, project) values (?, ?)");
        removeMemberStatement = link.prepareStatement("delete from TeamMembers where name = ? and project = ?");
        getStatement = link.prepareStatement("select * from Engineers where name = ?");
        getStoriesStatement = link.prepareStatement("select * from UserStories uS join UserAssignments uA on uS.id = uA.task where name = ?");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        addAssignmentStatement.close();
        removeAssignmentStatement.close();
        addStatement.close();
        removeStatement.close();
        getStatement.close();
        addMemberStatement.close();
        removeMemberStatement.close();
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

    protected void removeAssignmentQuery(String who, int what) {
        try {
            removeAssignmentStatement.setString(1, who);
            removeAssignmentStatement.setInt(2, what);

            int result = removeAssignmentStatement.executeUpdate();

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

    protected void removeMemberQuery(String who, String what) {
        try {
            removeMemberStatement.setString(1, who);
            removeMemberStatement.setString(2, what);

            int result = removeMemberStatement.executeUpdate();

            if (result == -1) {
                System.err.println("Failed to remove Engineer as a member of the project");
                cancel();
            } else {
                apply();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            cancel();
        }
    }

    protected ResultSet getQuery(String who) {
        try {
            getStatement.setString(1, who);
            return getStatement.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected ResultSet getAllQuery() {
        try {
            statement = link.createStatement();
            return statement.executeQuery("select * from Engineers");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
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
    protected void removeQuery(String who) {
        try {
            removeStatement.setString(1, who);

            int result = removeStatement.executeUpdate();

            if (result == -1) {
                System.err.println("Failed to remove Engineer");
                cancel();
            } else {
                apply();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            cancel();
        }
    }

    protected ResultSet getStoriesQuery(String who) {
        try {
            getStoriesStatement.setString(1, who);
            return getStoriesStatement.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
