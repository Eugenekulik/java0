package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * This class implement TransactionFactory for database MySQL.
 */

public class TransactionFactoryImpl implements TransactionFactory {
    private static final Logger LOGGER = LogManager.getLogger(TransactionFactoryImpl.class);


    @Override
    public Transaction createTransaction() throws DaoException {
        PooledConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("It is impossible to turn off autocommiting for database connection");
            throw new DaoException();
        }
        return new TransactionImpl(connection);
    }
}
