package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.CategoryDao;
import by.training.beautyParlor.dao.ProcedureDao;
import by.training.beautyParlor.dao.Transaction;
import by.training.beautyParlor.dao.TransactionFactory;
import by.training.beautyParlor.dao.mysql.TransactionFactoryImpl;
import by.training.beautyParlor.domain.Category;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcedureService {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureService.class);

    public List<Procedure> getProcedures() throws ServiceException {
        List<Procedure> procedures;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            procedures = procedureDao.findall();
            transaction.commit();
            if (procedures.isEmpty()) {
                return null;
            }
            return procedures;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException();
        }
    }

    public Procedure getProcedureByName(String name) throws ServiceException {
        Procedure procedure;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            procedure = procedureDao.findByName(name);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException();
        }
        return procedure;
    }

    public List<Category> getCategories() throws ServiceException {
        List<Category> categories;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao("categoryDao");
            categories = categoryDao.findall();
            transaction.commit();
            return categories;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException();
        }
    }
    public void addProcedure(Procedure procedure) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            procedureDao.create(procedure);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        }
    }
    public void deleteProcedure(Integer id) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            if (id != null) {
                procedureDao.delete(id);
            } else {
                LOGGER.warn(String.format("an error occurred while delete procedure by id: %d", id));
            }
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

    public void updateProcedure(Procedure procedure) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            procedureDao.update(procedure);
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
