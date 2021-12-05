package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.DaoException;
import by.training.beautyParlor.dao.GraphicDao;
import by.training.beautyParlor.dao.UserDao;
import by.training.beautyParlor.dao.mysql.GraphicDaoImpl;
import by.training.beautyParlor.dao.mysql.UserDaoImpl;
import by.training.beautyParlor.dao.pool.ConnectionPool;
import by.training.beautyParlor.dao.pool.PooledConnection;
import by.training.beautyParlor.domain.Graphic;
import by.training.beautyParlor.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.testng.Assert.*;

public class GraphicServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(GraphicServiceTest.class);
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

    @Test(priority = 1)
    public void testGraphicsByEmployee() {
        GraphicService graphicService = new GraphicService();
        LocalDate localDate = LocalDate.parse("2021-12-01");
        int employeeId = 2;
        try {
            List<LocalTime> graphics = graphicService.graphicsByEmployee(employeeId, localDate);
            assertEquals(graphics.size(),3);
        } catch (ServiceException e) {
            LOGGER.error(String.format("it is impossible to get graphics " +
                    "by employee with id: %d",employeeId),e);
        }
    }

    @Test(priority = 2)
    public void testAddGraphic() {
        LocalDate date = LocalDate.parse("2021-12-02");
        int employeeId = 2;
        GraphicService graphicService = new GraphicService();
        try {
            graphicService.addGraphic(employeeId, date);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                GraphicDao graphicDao = new GraphicDaoImpl();
                graphicDao.setConnection(connection);
                List<Graphic> actual  = graphicDao.findByEmployee(2);
                assertEquals(actual.size(),12);
                connection.close();
            } catch (DaoException e) {LOGGER.error(e);}
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to add graphic",e);
        }
    }

    @Test(priority = 3)
    public void testDeleteGraphic() {
        GraphicService graphicService = new GraphicService();
        try {
            graphicService.deleteGraphic(1);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                GraphicDao graphicDao = new GraphicDaoImpl();
                graphicDao.setConnection(connection);
                Graphic graphic = graphicDao.findById(1);
                assertNull(graphic);
                connection.close();
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting graphic");
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to delete graphic");
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
        } catch (SQLException |DaoException|IOException e) {LOGGER.error(e);}
    }
}