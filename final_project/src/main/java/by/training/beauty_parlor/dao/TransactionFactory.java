package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.exception.DaoException;

public interface TransactionFactory {
    Transaction createTransaction() throws DaoException;
}
