package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Procedure;

public interface ProcedureDao extends Dao<Procedure>{
    Procedure findByName(String name) throws DaoException;
}
