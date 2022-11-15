package by.training.beauty.dao.mysql;

import by.training.beauty.dao.Dao;
import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class implement Transaction for database MySQL.
 */

public class TransactionImpl implements Transaction {
    private static final Logger LOGGER = LogManager.getLogger(TransactionImpl.class);
    private Connection connection;

    public TransactionImpl(Connection connection){
        this.connection = connection;
    }


    @Override
    public <T extends Dao<?>> T createDao(String key) throws DaoException {
        T dao;
        switch (key){
            case "userDao":
                dao = (T)new UserDaoImpl();
                break;
            case "appointmentDao":
                dao = (T)new AppointmentDaoImpl();
                break;
            case "categoryDao":
                dao =  (T)new CategoryDaoImpl();
                break;
            case "procedureDao":
                dao =  (T)new ProcudureDaoImpl();
                break;
            case "procedureEmployeeDao":
                dao = (T)new ProcedureEmployeeDaoImpl();
                break;
            case "scoreDao":
                dao = (T)new ScoreDaoImpl();
                break;
            case "scheduleDao":
                dao = (T)new ScheduleDaoImpl();
                break;
            case "roleDao":
                dao = (T)new RoleDaoImpl();
                break;
            default:
                LOGGER.warn("No such Dao class");
                throw new DaoException();
        }
        dao.setConnection(connection);
        return dao;
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("It is impossible to commit connection");
            throw new DaoException();
        }
    }
    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("it is impossible to rollback database.connection");
            throw new DaoException();
        }
    }
}
