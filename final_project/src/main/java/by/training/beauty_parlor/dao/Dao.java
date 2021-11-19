package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.domain.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao<T extends Entity> {
    static final Logger LOGGER = LogManager.getLogger(Entity.class);
    List<T> findall()throws DaoException;
    T findById(int id)throws DaoException;
    boolean delete(int id) throws DaoException;
    boolean create(T t) throws DaoException;
    boolean update(T t)throws DaoException;
    void setConnection(Connection connection);
    default void close(Statement statement){
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
