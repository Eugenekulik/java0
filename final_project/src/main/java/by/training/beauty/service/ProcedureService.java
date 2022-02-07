package by.training.beauty.service;

import by.training.beauty.dao.CategoryDao;
import by.training.beauty.dao.ProcedureDao;
import by.training.beauty.dao.Transaction;
import by.training.beauty.dao.TransactionFactory;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.domain.Category;
import by.training.beauty.domain.Procedure;
import by.training.beauty.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This service allows you to do some activities with procedures.
 */
public class ProcedureService {
    //CONSTANTS
    private static final String PROCEDURE_DAO = "procedureDao";
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER
            = LogManager.getLogger(ProcedureService.class);

    /**
     * This method allows you to get all procedures.
     * @return List of procedures
     * @throws ServiceException
     */
    public List<Procedure> getProcedures() throws ServiceException {
        List<Procedure> procedures;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            procedures = procedureDao.findall();
            transaction.commit();
            return procedures;
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException();
        }
    }

    /**
     * This method allows you to get procedure by name.
     * @param name of the procedure
     * @return Procedure
     * @throws ServiceException
     */
    public Procedure getProcedureById(int id) throws ServiceException {
        Procedure procedure;
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            procedure = procedureDao.findById(id);
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException();
        }
        return procedure;
    }

    /**
     * This method allows you to get all categories of the procedures.
     * @return List of categories.
     * @throws ServiceException
     */
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
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException();
        }
    }

    /**
     * This method allows you to add procedure to the store.
     * @param procedure
     * @throws ServiceException
     */
    public void addProcedure(Procedure procedure) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            procedureDao.create(procedure);
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to delete procedure from the  store.
     * @param id identifier of the procedure.
     * @throws ServiceException
     */
    public void deleteProcedure(Integer id) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            if (id != null) {
                procedureDao.delete(id);
            } else {
                LOGGER.warn("an error occurred " +
                        "while delete procedure by id: {}", id);
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to update information about specific procedure.
     * @param procedure new procedure with old id.
     * @throws ServiceException
     */
    public void updateProcedure(Procedure procedure) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            procedureDao.update(procedure);
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
    }
}
