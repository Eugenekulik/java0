package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.User;
import by.training.beauty_parlor.exception.DaoException;

public interface UserDao extends Dao<User>{
    User findByLogin(String login, String password) throws DaoException;
    User findByLogin(String login) throws DaoException;
}
