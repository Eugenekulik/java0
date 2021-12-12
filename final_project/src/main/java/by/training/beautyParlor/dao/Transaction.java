package by.training.beautyParlor.dao;

/**
 * The Transaction interface allows operations to be performed against
 * the transaction in the target Transaction object.
 */

public interface Transaction {
    /**
     * This method allows getting special Dao by key.
     * @param key name of Dao.
     * @param <Type> Type of Dao.
     * @return Type extends Dao
     * @throws DaoException
     */
    <Type extends Dao<?>> Type createDao(String key) throws DaoException;

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
