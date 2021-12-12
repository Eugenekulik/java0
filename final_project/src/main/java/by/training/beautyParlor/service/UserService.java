package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.DaoException;
import by.training.beautyParlor.dao.Transaction;
import by.training.beautyParlor.dao.TransactionFactory;
import by.training.beautyParlor.dao.UserDao;
import by.training.beautyParlor.dao.mysql.TransactionFactoryImpl;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public User login(String login, String password) throws ServiceException {
        User user = null;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            String hexPassword = hexPassword(password);
            user = userDao.findByLogin(login, hexPassword);
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

    public User registrate(User user) throws ServiceException {
        user.setPassword(hexPassword(user.getPassword()));
        user.setRole("client");
        UserValidator userValidator =
                ServiceFactory.getInstance().getUserValidator();
        if(!(userValidator.loginValidator(user.getLogin())
        || userValidator.nameValidator(user.getName())
        || userValidator.passwordValidator(user.getPassword())
        || userValidator.phoneValidator(user.getPhone()))) {
            return null;
        }
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
    private String hexPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
        }
        byte[] passwordbytes = digest.digest(
               password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexPassword = new StringBuilder(2 * passwordbytes.length);
        for (int i = 0; i < passwordbytes.length; i++) {
            String hex = Integer.toHexString(0xff & passwordbytes[i]);
            if(hex.length() == 1) {
                hexPassword.append('0');
            }
            hexPassword.append(hex);
        }
        return hexPassword.toString();
    }
    public void deleteUser(Integer id) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            if (id != null) {
                userDao.delete(id);
            } else {
                LOGGER.warn(String.format("an error occurred while delete user by id: %d", id));
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction", e1);
            }
            throw new ServiceException(e);
        }
    }
    public void updateUser(User user) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            User actual = userDao.findById(user.getId());
            actual.setName(user.getName());
            actual.setPhone(user.getPhone());
            actual.setRole(user.getRole());
            userDao.update(actual);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw new ServiceException(e);
        }
    }
}
