package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.Procedure;
import by.training.beauty_parlor.exception.DaoException;

public interface ProcedureDao extends Dao<Procedure>{
    Procedure findByName(String name) throws DaoException;
}
