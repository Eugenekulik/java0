package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;

/**
 * This interface allows getting Transaction implementation.
 */
public interface TransactionFactory {
    /**
     * This method allows getting Transaction implementation.
     * @return Transaction
     * @throws DaoException
     */
    Transaction createTransaction() throws DaoException;
}
