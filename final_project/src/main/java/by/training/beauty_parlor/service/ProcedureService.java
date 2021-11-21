package by.training.beauty_parlor.service;

import by.training.beauty_parlor.dao.CategoryDao;
import by.training.beauty_parlor.dao.ProcedureDao;
import by.training.beauty_parlor.dao.Transaction;
import by.training.beauty_parlor.dao.TransactionFactory;
import by.training.beauty_parlor.dao.mysql.TransactionFactoryImpl;
import by.training.beauty_parlor.domain.Category;
import by.training.beauty_parlor.domain.Procedure;
import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcedureService {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureService.class);

    public List<Procedure> getProceduresName() throws ServiceException {
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

    public List<String> getCategoriesName() throws ServiceException {
        List<Category> categories;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao("categoryDao");
            categories = categoryDao.findall();
            transaction.commit();
            if (categories.isEmpty()) {
                return null;
            }
            return categories.stream().map(category -> {
                        return category.getName();
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
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
