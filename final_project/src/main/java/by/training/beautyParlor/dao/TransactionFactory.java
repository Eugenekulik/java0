package by.training.beautyParlor.dao;

public interface TransactionFactory {
    Transaction createTransaction() throws DaoException;
}
