package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    User findByLogin(String login, String password) throws DaoException;
    User findByLogin(String login) throws DaoException;
    List<User> findEmployees() throws DaoException;
    User findByName(String name) throws DaoException;
}
