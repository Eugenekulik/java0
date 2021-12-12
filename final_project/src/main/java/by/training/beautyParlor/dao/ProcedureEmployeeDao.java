package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.domain.ProcedureEmployee;
import by.training.beautyParlor.domain.User;

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
    List<ProcedureEmployee> findByProcedure(Procedure procedure)throws DaoException;
    ProcedureEmployee findByProcedureEmployee(Procedure procedure, User employee) throws DaoException;
}
