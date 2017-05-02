package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexsaenen on 5/1/17.
 */
public class TeamORM extends ORM {

    protected PreparedStatement getMemberStatement;
    protected PreparedStatement getAllMembersStatement;

    @Override
    protected void CreateStatements() throws SQLException {
        getMemberStatement = link.prepareStatement("select * from TeamMembers where name = ? and project = ?");
        getAllMembersStatement = link.prepareStatement("select * from Engineers eng join TeamMembers tm on eng.name = tm.name where tm.project = ?");
    }

    @Override
    protected void CloseStatements() throws SQLException {
        getMemberStatement.close();
        getAllMembersStatement.close();
    }

    protected ResultSet getMemberQuery(String who, String project) {
        try {
            getMemberStatement.setString(1, who);
            getMemberStatement.setString(2, project);
            return getMemberStatement.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    protected ResultSet getAllMembersQuery(String project) {
        try {
            getAllMembersStatement.setString(1, project);
            return getAllMembersStatement.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
