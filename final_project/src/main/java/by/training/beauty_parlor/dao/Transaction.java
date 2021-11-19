package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.exception.DaoException;

public interface Transaction {
    <Type extends Dao<?>> Type createDao(String key) throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;
}
