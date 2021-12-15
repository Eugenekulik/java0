package by.training.beauty.dao;

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
}
