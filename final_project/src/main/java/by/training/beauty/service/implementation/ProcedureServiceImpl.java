package by.training.beauty.service.implementation;

import by.training.beauty.dao.spec.CategoryDao;
import by.training.beauty.dao.spec.ProcedureDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Category;
import by.training.beauty.domain.Procedure;
import by.training.beauty.dao.DaoException;
import by.training.beauty.service.spec.ProcedureService;
import by.training.beauty.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This service allows you to do some activities with procedures.
 */
public class ProcedureServiceImpl implements ProcedureService {
    //CONSTANTS
    private static final String PROCEDURE_DAO = "procedureDao";
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER
            = LogManager.getLogger(ProcedureService.class);
    private TransactionFactory transactionFactory;

    public ProcedureServiceImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    /**
     * This method allows you to get all procedures.
     * @return List of procedures
     * @throws ServiceException
     */
    @Override public List<Procedure> getProcedures() throws ServiceException {
        List<Procedure> procedures;
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
     * @param id of the procedure
     * @return Procedure
     * @throws ServiceException
     */
    @Override public Procedure getProcedureById(int id) throws ServiceException {
        Procedure procedure;
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
    @Override public List<Category> getCategories() throws ServiceException {
        List<Category> categories;
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
     * @return
     */
    @Override public boolean addProcedure(Procedure procedure) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            Procedure result = procedureDao.create(procedure);
            transaction.commit();
            return result != null;
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
     * @return
     */
    @Override public boolean deleteProcedure(int id) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            boolean result = procedureDao.delete(id);
            transaction.commit();
            return result;
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
     * @return
     */
    @Override public boolean updateProcedure(Procedure procedure) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            boolean result = procedureDao.update(procedure);
            transaction.commit();
            return result;
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
