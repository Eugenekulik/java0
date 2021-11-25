package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.Procedure;

public interface ProcedureDao extends Dao<Procedure>{
    Procedure findByName(String name) throws DaoException;
}
