package by.training.beauty.service;

import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.ProcedureEmployeeDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.dao.spec.UserDao;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.domain.User;
import by.training.beauty.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This service allows you to do some activities with employee.
 */
public class EmployeeService {
    private static final Logger LOGGER
            = LogManager.getLogger(EmployeeService.class);

    /**
     * This method allows you to get list of employee.
     * @return List of users which roles are employee
     * @throws ServiceException
     */
    public List<User> employeeList() throws ServiceException {
        List<User> employeeList;
        TransactionFactory transactionFactory;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
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

    /**
     * This method allows you to get employees which can do specific procedure.
     * @param procedure specific procedure.
     * @return List of users which roles are employee.
     * @throws ServiceException
     */
    public List<User> employeesByProcedure(Procedure procedure)
            throws ServiceException {
        List<User> employeeList = new ArrayList<>();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
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
}
