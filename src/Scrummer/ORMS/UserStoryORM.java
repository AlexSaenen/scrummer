package Scrummer.ORMS;

import Scrummer.ORM;

import java.sql.SQLException;

/**
 * Created by nicolasgirardot on 4/19/17.
 */
public class UserStoryORM extends ORM {


    public UserStoryORM() {
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
