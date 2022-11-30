package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.CategoryDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Category;
import by.training.beauty.service.spec.CategoryService;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CategoryServiceImplTest {

    TransactionFactory transactionFactory;

    @BeforeClass
    public void init(){
        transactionFactory = mock(TransactionFactory.class);
        Transaction transaction = mock(Transaction.class);
        CategoryDao categoryDao = mock(CategoryDao.class);
        try{
            when(categoryDao.findall()).thenReturn(List.of(new Category.Builder()
                    .setId(1)
                    .setName("category")
                    .setDescription("description")
                    .build()));
            when(transaction.createDao(DaoEnum.CATEGORY.getDao())).thenReturn(categoryDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (DaoException e){e.printStackTrace();}
    }


    @Test
    public void testGetAllCategories(){
        CategoryService categoryService = new CategoryServiceImpl(transactionFactory);
        Assertions.assertThat(categoryService.getAllCategories())
                .usingRecursiveFieldByFieldElementComparator()
                .contains(new Category.Builder()
                        .setId(1)
                        .setName("category")
                        .setDescription("description")
                        .build())
                .size().isEqualTo(1);
    }

}