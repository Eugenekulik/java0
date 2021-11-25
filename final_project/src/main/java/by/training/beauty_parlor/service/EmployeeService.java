package by.training.beauty_parlor.service;

import by.training.beauty_parlor.dao.*;
import by.training.beauty_parlor.dao.mysql.TransactionFactoryImpl;
import by.training.beauty_parlor.domain.Procedure;
import by.training.beauty_parlor.domain.ProcedureEmployee;
import by.training.beauty_parlor.domain.User;
import by.training.beauty_parlor.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);
    public void employeeList(HttpServletRequest request) throws ServiceException {
        List<User> employeeList = new ArrayList<>();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            employeeList = userDao.findEmployees();
            request.getSession().setAttribute("employeeList", employeeList);
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
    public void employeesByProcedure(HttpServletRequest request) throws ServiceException {
        List<User> employeeList = new ArrayList<>();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            ProcedureEmployeeDao procedureEmployeeDao = transaction.createDao("procedureEmployeeDao");
            Set<Integer> employeeIdSet = procedureEmployeeDao.
                    findByProcedure((Procedure) request.getSession().getAttribute("procedure")).stream().
                    map(ProcedureEmployee::getEmployeeId).collect(Collectors.toSet());
            employeeList = userDao.findEmployees();
            transaction.commit();
            request.getSession().setAttribute("employeeList", employeeList.stream()
                    .filter(user -> {return employeeIdSet.contains(user.getId());}).
                    collect(Collectors.toList()));
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
