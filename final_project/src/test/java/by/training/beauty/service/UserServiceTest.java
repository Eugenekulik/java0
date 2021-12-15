package by.training.beauty.service;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.UserDao;
import by.training.beauty.dao.mysql.UserDaoImpl;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.domain.User;
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

public class UserServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateServiceTest.class);

    @BeforeClass
    public void init() {
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
    public void testLogin() {
        UserService userService = new UserService();
        try {
            User user = userService.login("thirduser","thirduser");
            assertNotNull(user);
        } catch (ServiceException e){
            LOGGER.error("it is impossible to authorizate",e);
        }
    }

    @Test
    public void testRegistrate() {
        UserService userService =  new UserService();
        User user = new User();
        user.setName("new user");
        user.setLogin("newuser");
        user.setPassword("password");
        user.setPhone("+375337748378");
        try {
            User actual = userService.registrate(user);
            assertNotNull(actual);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to registrate user",e);
        }
    }

    @Test(priority = 1)
    public void testDeleteUser() {
        UserService userService = new UserService();
        try {
            userService.deleteUser(3);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                UserDao userDao = new UserDaoImpl();
                userDao.setConnection(connection);
                User user = userDao.findById(3);
                assertNull(user);
                connection.close();
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting user",e);
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to delete user",e );
        }
    }

    @Test
    public void testUpdateUser() {
        UserService userService = new UserService();
        User user = new User();
        user.setId(3);
        user.setRole("employee");
        user.setPhone("+375293333333");
        user.setName("thirduser");
        try {
            userService.updateUser(user);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                UserDao userDao = new UserDaoImpl();
                userDao.setConnection(connection);
                User actual = userDao.findById(3);
                assertEquals(actual.getRole(), "employee");
                connection.close();
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting user", e);
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to update user", e);
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
            ConnectionPoolService connectionPoolService = new ConnectionPoolService();
            connectionPoolService.destroy();
        } catch (SQLException |DaoException|IOException e) {LOGGER.error(e);}
    }
}