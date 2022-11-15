package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;
import by.training.beauty.domain.Entity;

import java.sql.Connection;
import java.util.List;

/**
 * This interface allows getting Entity from store.
 * Data access object pattern.
 *
 * @param <T> with which kind of Entity we work.
 */

public interface Dao<T extends Entity> {
    List<T> findall()throws DaoException;
    List<T> findInterval(int begin, int count)throws DaoException;
    int count()throws DaoException;
    T findById(int id)throws DaoException;
    boolean delete(int id) throws DaoException;
    int create(T t) throws DaoException;
    boolean update(T t)throws DaoException;
    void setConnection(Connection connection);
}
