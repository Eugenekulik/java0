package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.domain.ProcedureEmployee;
import by.training.beautyParlor.domain.User;

import java.util.List;

public interface ProcedureEmployeeDao extends Dao<ProcedureEmployee>{
    List<ProcedureEmployee> findByProcedure(Procedure procedure)throws DaoException;
    ProcedureEmployee findByProcedureEmployee(Procedure procedure, User employee) throws DaoException;
}
