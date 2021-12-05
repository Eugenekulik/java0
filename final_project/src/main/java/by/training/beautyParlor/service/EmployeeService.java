package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.*;
import by.training.beautyParlor.dao.mysql.TransactionFactoryImpl;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.domain.ProcedureEmployee;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);
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
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw new ServiceException(e);
        }
        return employeeList;
    }
    public List<User> employeesByProcedure(Procedure procedure) throws ServiceException {
        List<User> employeeList = new ArrayList<>();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            ProcedureEmployeeDao procedureEmployeeDao = transaction.createDao("procedureEmployeeDao");
            Set<Integer> employeeIdSet = procedureEmployeeDao.
                    findByProcedure(procedure).stream().
                    map(ProcedureEmployee::getEmployeeId).collect(Collectors.toSet());
            employeeList = userDao.findEmployees();
            transaction.commit();
            return employeeList.stream()
                    .filter(user -> {return employeeIdSet.contains(user.getId());}).
                    collect(Collectors.toList());
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction",e1);
            }
            throw new ServiceException(e);
        }
    }
}
