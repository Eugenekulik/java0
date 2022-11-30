package by.training.beauty.service.implementation;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.UserService;
import by.training.beauty.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * This service allows you to do some activities with users.
 */
public class UserServiceImpl implements UserService {
    //CONSTANTS
    private static final String USER_DAO = "userDao";
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private TransactionFactory transactionFactory;

    public UserServiceImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    /**
     * Authorization.
     * @param login user login
     * @param password user password
     * @return User if success, else null.
     * @throws ServiceException
     */
    @Override public User login(String login, String password) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            String hexPassword = hexPassword(password);
            User user = userDao.findByLogin(login);
            if(user == null || !user.getPassword().equals(hexPassword))return null;
            roleDao.findByUser(user.getId()).stream().forEach(user::addRole);
            transaction.commit();
            return user;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException();
        }
    }

    /**
     * Registration.
     * @param user
     * @return  user if success, else null.
     * @throws ServiceException
     */
    @Override public User registration(User user) throws ServiceException {
        user.setPassword(hexPassword(user.getPassword()));
        UserValidator userValidator =
                ServiceFactory.getInstance().getUserValidator();
        if (!(userValidator.loginValidator(user.getLogin())
                || userValidator.nameValidator(user.getName())
                || userValidator.passwordValidator(user.getPassword())
                || userValidator.phoneValidator(user.getPhone()))) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            User temp = userDao.findByLogin(user.getLogin());
            if (temp == null) {
                user = userDao.create(user);
                user.addRole(new Role("client"));
                roleDao.updateRolesForUser(user);
                transaction.commit();
                return user;
            } else {
                return null;
            }
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR,e1);
            }
            throw new ServiceException();
        }
    }

    /**
     * This method allows you to delete user from the store.
     * @param id identifier of user.
     * @throws ServiceException
     * @return
     */
    @Override public boolean deleteUser(int id) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(USER_DAO);
            boolean result = userDao.delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR, e1);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method hash password(sha-256).
     *
     * @param password user password.
     * @return hash-password.
     */
    private String hexPassword(String password) {
        MessageDigest digest = null;
        byte[] passwordbytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            passwordbytes = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            UserServiceImpl.LOGGER.error(e);
        }
        StringBuilder hexPassword = new StringBuilder(2 * passwordbytes.length);
        for (int i = 0; i < passwordbytes.length; i++) {
            String hex = Integer.toHexString(0xff & passwordbytes[i]);
            if (hex.length() == 1) {
                hexPassword.append('0');
            }
            hexPassword.append(hex);
        }
        return hexPassword.toString();
    }

    /**
     * This method allows you to update information about user.
     * @param user new user with old id.
     * @throws ServiceException
     * @return
     */
    @Override public boolean updateUser(User user) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            User actual = userDao.findById(user.getId());
            boolean result;
            if(actual != null) {
                actual.setName(user.getName());
                actual.setPhone(user.getPhone());
                roleDao.updateRolesForUser(user);
                userDao.update(actual);
                result = true;
            } else result = false;
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR,e1);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to get employees which can do specific procedure.
     * @param procedure specific procedure.
     * @return List of users which roles are employee.
     * @throws ServiceException
     */
    @Override public List<User> employeesByProcedure(Procedure procedure)
            throws ServiceException {
        List<User> employees;
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            employees = userDao.findEmployeesByProcedure(procedure.getId());
            transaction.commit();
            return employees;
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction",e1);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to get list of employee.
     * @return List of users which roles are employee
     * @throws ServiceException
     */
    @Override public List<User> employeeList() throws ServiceException {
        List<User> employeeList;
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            employeeList = userDao.findEmployees();
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw new ServiceException(e);
        }
        return employeeList;
    }

    @Override public User findById(int id){
        Transaction transaction = null;
        User user = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            user  = userDao.findById(id);
            transaction.commit();
        } catch (DaoException e){
            try {
                if(transaction != null){
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
        }
        return user;
    }
}
