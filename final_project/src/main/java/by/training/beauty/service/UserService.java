package by.training.beauty.service;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import by.training.beauty.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This service allows you to do some activities with users.
 */
public class UserService {
    //CONSTANTS
    private static final String USER_DAO = "userDao";
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private TransactionFactory transactionFactory;

    public UserService(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    /**
     * Authorization.
     * @param login user login
     * @param password user password
     * @return User if success, else null.
     * @throws ServiceException
     */
    public User login(String login, String password) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            UserDao userDao = transaction.createDao(USER_DAO);
            String hexPassword = hexPassword(password);
            User user = userDao.findByLogin(login, hexPassword);
            if(user!=null){
                roleDao.findByUser(user).stream().forEach(user::addRole);
            }
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
    public User registrate(User user) throws ServiceException {
        user.setPassword(hexPassword(user.getPassword()));
        user.addRole(new Role("client"));
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
            UserDao userDao = transaction.createDao(USER_DAO);
            User temp = userDao.findByLogin(user.getLogin());
            if (temp == null) {
                userDao.create(user);
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
     * This method hash password(sha-256).
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
            LOGGER.error(e);
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
     * This method allows you to delete user from the store.
     * @param id identifier of user.
     * @throws ServiceException
     */
    public void deleteUser(Integer id) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(USER_DAO);
            if (id != null) {
                userDao.delete(id);
            } else {
                LOGGER.warn("an error occurred while delete user by id: {}", id);
            }
            transaction.commit();
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
     * This method allows you to update information about user.
     * @param user new user with old id.
     * @throws ServiceException
     */
    public void updateUser(User user) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            User actual = userDao.findById(user.getId());
            actual.setName(user.getName());
            actual.setPhone(user.getPhone());
            roleDao.updateRolesForUser(user);
            userDao.update(actual);
            transaction.commit();
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
    public List<User> employeesByProcedure(Procedure procedure)
            throws ServiceException {
        List<User> employeeList;
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            ProcedureEmployeeDao procedureEmployeeDao
                    = transaction.createDao("procedureEmployeeDao");
            Set<Integer> employeeIdSet = procedureEmployeeDao.
                    findByProcedure(procedure).stream().
                    map(ProcedureEmployee::getEmployeeId)
                    .collect(Collectors.toSet());
            employeeList = userDao.findEmployees();
            transaction.commit();
            return employeeList.stream()
                    .filter(user -> employeeIdSet.contains(user.getId())).
                    collect(Collectors.toList());
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
    public List<User> employeeList() throws ServiceException {
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

    public User findById(int id){
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
