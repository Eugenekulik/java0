package by.training.beauty_parlor.service;

import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.dao.Transaction;
import by.training.beauty_parlor.dao.TransactionFactory;
import by.training.beauty_parlor.dao.UserDao;
import by.training.beauty_parlor.dao.mysql.TransactionFactoryImpl;
import by.training.beauty_parlor.domain.User;
import by.training.beauty_parlor.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public User login(String login, String password) throws ServiceException {
        User user = null;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            user = userDao.findByLogin(login, password);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException();
        }
        return user;
    }

    public User registrate(HttpServletRequest request) throws ServiceException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setRole("client");
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            User temp = userDao.findByLogin(user.getLogin());
            if (temp == null &&userDao.create(user)) {
                transaction.commit();
                return user;
            } else {
                return null;
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException();
        }
    }
}
