package by.training.beauty.dao;

import by.training.beauty.domain.User;

import java.util.List;

/**
 * This interface extends Dao for User.
 */
public interface UserDao extends Dao<User>{
    /**
     * This method find user by login and password if existed, else return null.
     * @param login user login
     * @param password user password
     * @return User
     * @throws DaoException
     */
    User findByLogin(String login, String password) throws DaoException;

    /**
     * This method find user by login if existed, else return null;
     * @param login user login
     * @return User
     * @throws DaoException
     */
    User findByLogin(String login) throws DaoException;

    /**
     * This method find all users with role employee.
     * if there is none return empty list.
     * @return List of users.
     * @throws DaoException
     */
    List<User> findEmployees() throws DaoException;

    /**
     * This method find user by name if exist, else return null.
     * @param name user name
     * @return User
     * @throws DaoException
     */
    User findByName(String name) throws DaoException;
}
