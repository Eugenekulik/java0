package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    User findByLogin(String login, String password) throws DaoException;
    User findByLogin(String login) throws DaoException;
    List<User> findEmployees() throws DaoException;
    User findByName(String name) throws DaoException;
}
