package by.training.beautyParlor.dao.mysql;

import by.training.beautyParlor.dao.DaoException;
import by.training.beautyParlor.dao.Transaction;
import by.training.beautyParlor.dao.TransactionFactory;
import by.training.beautyParlor.dao.pool.ConnectionPool;
import by.training.beautyParlor.dao.pool.PooledConnection;
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
        Transaction transaction = new TransactionImpl(connection);
        return transaction;
    }
}
