package by.training.beauty.dao;

import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.domain.User;

import java.util.List;

/**
 * This interface extends Dao for ProcedureEmployee.
 *
 * @see Dao
 * @see ProcedureEmployee
 */

public interface ProcedureEmployeeDao extends Dao<ProcedureEmployee>{

    /**
     * This method allows getting ProcedureEmployee by Procedure.
     *
     * @param procedure
     * @return ProcedureEmployee
     * @throws DaoException
     */
    List<ProcedureEmployee> findByEmployee(User employee) throws DaoException;
    List<ProcedureEmployee> findByProcedure(Procedure procedure)throws DaoException;
    ProcedureEmployee findByProcedureEmployee(Procedure procedure, User employee) throws DaoException;
}
