package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.DaoException;
import by.training.beautyParlor.dao.pool.ConnectionPool;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.*;

public class EmployeeServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);
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
    public void testEmployeeList() {
        EmployeeService employeeService = new EmployeeService();
        try {
            List<User> employeeList = employeeService.employeeList();
            assertEquals(employeeList.size(), 1);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get employee list");
        }
    }

    @Test
    public void testEmployeesByProcedure() {
        Procedure procedure = new Procedure();
        procedure.setId(1);
        EmployeeService employeeService = new EmployeeService();
        try {
            List<User> employeeList = employeeService.employeesByProcedure(procedure);
            assertEquals(employeeList.size(), 1);
        } catch (ServiceException e) {
            LOGGER.error(String.format("it is impossible to get employee " +
                    "list by procedure with id: %D", procedure.getId()));
        }
    }

    @AfterClass
    public void destroy(){
        ConnectionPoolService connectionPoolService = new ConnectionPoolService();
        connectionPoolService.destroy();
    }
}