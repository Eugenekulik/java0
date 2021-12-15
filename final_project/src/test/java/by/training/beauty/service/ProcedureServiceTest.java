package by.training.beauty.service;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.ProcedureDao;
import by.training.beauty.dao.mysql.ProcudureDaoImpl;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.domain.Category;
import by.training.beauty.domain.Procedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static org.testng.Assert.*;

public class ProcedureServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureServiceTest.class);
    @BeforeClass
    public void init(){
        Properties properties = new Properties();
        try {
            URL resource = getClass().getClassLoader().getResource("connection.properties");
            properties.load(new FileReader(new File(resource.toURI())));
            ConnectionPool.getInstance().init(properties.getProperty("db.driver"), properties.getProperty("db.url"),
                    properties.getProperty("user"), properties.getProperty("password"), 1, 4, 30);
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("It is impossible to load properties", e);
        } catch (DaoException e) {
            LOGGER.error("It is impossible to init connection pool to database", e);
        }
    }
    @Test
    public void testGetProcedures() {
        ProcedureService procedureService = new ProcedureService();
        try {
            List<Procedure> procedures = procedureService.getProcedures();
            assertEquals(procedures.size(), 6);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get procedures",e);
        }
    }

    @Test
    public void testGetProcedureByName() {
        ProcedureService procedureService =  new ProcedureService();
        String name = "Лазерное омоложение";
        try {
            Procedure procedure = procedureService.getProcedureByName(name);
            assertEquals(procedure.getId(),1);
        } catch (ServiceException e) {
            LOGGER.error(String.format("it is impossible to get procedure by name: %s",name),e);
        }
    }

    @Test
    public void testGetCategories() {
        ProcedureService procedureService = new ProcedureService();
        try {
            List<Category> categories = procedureService.getCategories();
            assertEquals(categories.size(), 6);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get categories",e);
        }
    }

    @Test(priority = 1)
    public void testAddProcedure() {
        Procedure procedure = new Procedure();
        ProcedureService procedureService = new ProcedureService();
        procedure.setName("Лазерный карбоновый пилинг");
        procedure.setDescription("Лазерный карбоновый пилинг");
        procedure.setElapsedTime(60);
        procedure.setCategoryId(1);
        try {
            procedureService.addProcedure(procedure);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                ProcedureDao procedureDao = new ProcudureDaoImpl();
                procedureDao.setConnection(connection);
                procedure = procedureDao.findByName("Лазерный карбоновый пилинг");
                assertNotNull(procedure);
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting procedure",e);
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to add procedure",e);
        }
    }

    @Test(priority = 2)
    public void testDeleteProcedure() {
        ProcedureService procedureService = new ProcedureService();
        try {
            procedureService.deleteProcedure(1);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                ProcedureDao procedureDao = new ProcudureDaoImpl();
                procedureDao.setConnection(connection);
                Procedure procedure = procedureDao.findById(1);
                assertNull(procedure);
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting procedure");
            }
        } catch (ServiceException e){
            LOGGER.error(String.format("it is impossible to delete procedure by id: %d", 1),e);
        }
    }

    @Test(priority = 1)
    public void testUpdateProcedure(){
        ProcedureService procedureService = new ProcedureService();
        Procedure procedure = new Procedure();
        procedure.setId(1);
        procedure.setCategoryId(1);
        procedure.setElapsedTime(60);
        procedure.setName("change");
        procedure.setDescription("change");
        try {
            procedureService.updateProcedure(procedure);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                ProcedureDao procedureDao = new ProcudureDaoImpl();
                procedureDao.setConnection(connection);
                procedure = procedureDao.findById(1);
                assertEquals(procedure.getName(), "change");
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting procedure");
            }
        } catch (ServiceException e){
            LOGGER.error("it is impossible to update procedure",e);
        }
    }
    @AfterClass
    public void destroy(){
        try {
            URL resource = getClass().getClassLoader().getResource("init_test.sql");
            Scanner scanner = new Scanner(new FileReader(resource.getFile()));
            scanner.useDelimiter(";");
            List<String> queries = new ArrayList<>();
            while (scanner.hasNext()){
                queries.add(scanner.next() + ";");
            }
            PooledConnection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            for (String s:queries) {
                statement.executeUpdate(s);
            }
            connection.close();
            ConnectionPoolService connectionPoolService = new ConnectionPoolService();
            connectionPoolService.destroy();
        } catch (SQLException | DaoException | IOException e) {LOGGER.error(e);}
    }
}