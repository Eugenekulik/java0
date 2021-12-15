package by.training.beauty.dao;

/**
 * The Transaction interface allows operations to be performed against
 * the transaction in the target Transaction object.
 */

public interface Transaction {
    /**
     * This method allows getting special Dao by key.
     * @param key name of Dao.
     * @param <T> T of Dao.
     * @return T extends Dao
     * @throws DaoException
     */
    <T extends Dao<?>> T createDao(String key) throws DaoException;

    /**
     * Complete the transaction represented by this Transaction object.
     * @throws DaoException
     */
    void commit() throws DaoException;

    /**
     * Rollback the transaction represented by this Transaction object.
     * @throws DaoException
     */
    void rollback() throws DaoException;
}
