package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.dao.Dao;
import by.training.beauty_parlor.dao.DaoException;
import by.training.beauty_parlor.dao.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {
    private static final Logger LOGGER = LogManager.getLogger(TransactionImpl.class);
    private Connection connection;

    public TransactionImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type createDao(String key) throws DaoException {
        Type dao = null;
        switch (key){
            case "userDao":
                dao = (Type)new UserDaoImpl();
                break;
            case "appointmentDao":
                dao = (Type)new AppointmentDaoImpl();
                break;
            case "categoryDao":
                dao =  (Type)new CategoryDaoImpl();
                break;
            case "procedureDao":
                dao =  (Type)new ProcudureDaoImpl();
                break;
            case "procedureEmployeeDao":
                dao = (Type)new ProcedureEmployeeDaoImpl();
                break;
            case "scoreDao":
                dao = (Type)new ScoreDaoImpl();
                break;
            case "graphicDao":
                dao = (Type)new GraphicDaoImpl();
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
        } finally {

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
