package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.Procedure;
import by.training.beauty_parlor.domain.ProcedureEmployee;
import by.training.beauty_parlor.domain.User;

import java.util.List;

public interface ProcedureEmployeeDao extends Dao<ProcedureEmployee>{
    List<ProcedureEmployee> findByProcedure(Procedure procedure)throws DaoException;
    ProcedureEmployee findByProcedureEmployee(Procedure procedure, User employee) throws DaoException;
}
