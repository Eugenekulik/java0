package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.CategoryDao;
import by.training.beauty.dao.spec.ProcedureDao;
import by.training.beauty.dao.mysql.ProcedureDaoImpl;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Category;
import by.training.beauty.domain.Procedure;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.ConnectionPoolService;
import by.training.beauty.service.spec.ProcedureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class ProcedureServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureServiceImplTest.class);
    TransactionFactory transactionFactory;

    @BeforeClass
    public void init(){
        Transaction transaction = mock(Transaction.class);
        transactionFactory = mock(TransactionFactoryImpl.class);
        ProcedureDao procedureDao = mock(ProcedureDao.class);
        CategoryDao categoryDao = mock(CategoryDao.class);
        try{

// Configure mock procedureDao
            when(procedureDao.findall())
                    .thenReturn(List.of(new Procedure.Builder()
                            .setId(1)
                            .setName("procedure")
                            .setElapsedTime(60)
                            .setCategoryId(1)
                            .setDescription("description")
                            .build()));
            when(procedureDao.findById(1)).thenReturn(new Procedure.Builder()
                    .setId(1)
                    .setName("procedure")
                    .setElapsedTime(60)
                    .setCategoryId(1)
                    .setDescription("description")
                    .build());
            when(procedureDao.create(any())).thenReturn(new Procedure());
            when(procedureDao.delete(anyInt())).thenReturn(true);
            when(procedureDao.update(any())).thenReturn(true);


//Configure mock categoryDao
            when(categoryDao.findall())
                    .thenReturn(List.of(new Category.Builder()
                            .setId(1)
                            .setName("category")
                            .setDescription("description")
                            .build()));


            when(transaction.createDao(DaoEnum.CATEGORY.getDao())).thenReturn(categoryDao);
            when(transaction.createDao(DaoEnum.PROCEDURE.getDao())).thenReturn(procedureDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (DaoException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testGetProcedures() {
        try {
            ProcedureService procedureService = new ProcedureServiceImpl(transactionFactory);
            Assertions.assertThat(procedureService.getProcedures())
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new Procedure.Builder()
                            .setId(1)
                            .setName("procedure")
                            .setElapsedTime(60)
                            .setCategoryId(1)
                            .setDescription("description")
                            .build())
                    .size().isEqualTo(1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetProcedureById() {
        try {
            ProcedureService procedureService = new ProcedureServiceImpl(transactionFactory);
            Assertions.assertThat(procedureService.getProcedureById(1))
                    .usingRecursiveComparison().isEqualTo(new Procedure.Builder()
                            .setId(1)
                            .setName("procedure")
                            .setElapsedTime(60)
                            .setCategoryId(1)
                            .setDescription("description")
                            .build());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCategories() {
        try {
            ProcedureService procedureService = new ProcedureServiceImpl(transactionFactory);
            Assertions.assertThat(procedureService.getCategories())
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new Category.Builder()
                            .setId(1)
                            .setName("category")
                            .setDescription("description")
                            .build())
                    .size().isEqualTo(1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void testAddProcedure() {
        Procedure procedure = new Procedure();
        procedure.setName("Лазерный карбоновый пилинг");
        procedure.setDescription("Лазерный карбоновый пилинг");
        procedure.setElapsedTime(60);
        procedure.setCategoryId(1);
        try {
            ProcedureService procedureService = new ProcedureServiceImpl(transactionFactory);
            Assertions.assertThat(procedureService.addProcedure(procedure)).isTrue();
            verify(((ProcedureDao)transactionFactory
                    .createTransaction()
                    .createDao(DaoEnum.PROCEDURE.getDao())), times(1)).create(any());
        } catch (ServiceException|DaoException e) {
            e.printStackTrace();
        }
    }

   @Test(priority = 2)
    public void testDeleteProcedure() {
        try {
            ProcedureService procedureService = new ProcedureServiceImpl(transactionFactory);
            Assertions.assertThat(procedureService.deleteProcedure(1)).isTrue();
        } catch (ServiceException e){
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void testUpdateProcedure(){
        Procedure procedure = new Procedure();
        procedure.setId(1);
        procedure.setCategoryId(1);
        procedure.setElapsedTime(60);
        procedure.setName("change");
        procedure.setDescription("change");
        try {
            ProcedureService procedureService = new ProcedureServiceImpl(transactionFactory);
            Assertions.assertThat(procedureService.updateProcedure(procedure)).isTrue();
        } catch (ServiceException e){
            e.printStackTrace();
        }
    }
    @AfterClass
    public void destroy(){
    }
}