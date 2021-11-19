package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.dao.Transaction;
import by.training.beauty_parlor.dao.TransactionFactory;
import by.training.beauty_parlor.dao.pool.ConnectionPool;
import by.training.beauty_parlor.dao.pool.PooledConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

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
