package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;
import by.training.beauty.domain.Procedure;

/**
 * This interdace extends Dao for Procedure.
 *
 * @see Dao
 * @see Procedure
 */

public interface ProcedureDao extends Dao<Procedure>{

    /**
     * This method allows getting procedure by name.
     * @param name procedure's name.
     * @return Procedure
     * @throws DaoException
     */
    Procedure findByName(String name) throws DaoException;

    Procedure findByProcedureEmployee(int procedureEmplyeeId) throws DaoException;
}
