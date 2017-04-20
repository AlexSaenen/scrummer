package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class ProjectORM extends ORM{

    public ProjectORM() {
        try {
            CreateStatements();
            isPrepared = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected void CreateStatements() throws SQLException {

    }

    @Override
    protected void CloseStatements() throws SQLException {

    }

}
