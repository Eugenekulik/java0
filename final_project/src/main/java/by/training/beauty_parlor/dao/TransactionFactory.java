package by.training.beauty_parlor.dao;

public interface TransactionFactory {
    Transaction createTransaction() throws DaoException;
}
