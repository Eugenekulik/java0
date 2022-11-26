package by.training.beauty.service;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.CategoryDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class CategoryService {
    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

    public List<Category> getAllCategories(){
        Transaction transaction = null;
        try {
            transaction = ServiceFactory.getInstance()
                    .getTransactionFactory().createTransaction();
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            List<Category> categories = categoryDao.findall();
            transaction.commit();
            return categories;
        } catch (DaoException e){
            LOGGER.error("Error occurred while getting all categories");
            try {
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                LOGGER.error("Error occurred while rollback transaction");
            }
        }
        return Collections.emptyList();
    }
}
