package Scrummer.Controllers;

import Scrummer.ORMS.BacklogORM;

/**
 * Created by alexsaenen on 4/19/17.
 */
public class Backlogs extends BacklogORM {

    public int create(boolean isSprint) {
        return createQuery(isSprint);
    }
}
