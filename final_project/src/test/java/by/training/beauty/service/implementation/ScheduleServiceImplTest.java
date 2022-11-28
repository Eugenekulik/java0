package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.ScheduleDao;
import by.training.beauty.dao.mysql.ScheduleDaoImpl;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.domain.Schedule;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.ConnectionPoolService;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.testng.Assert.*;

public class ScheduleServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleServiceImplTest.class);
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
    public void testSchedulesByEmployee() {
        LocalDate localDate = LocalDate.parse("2021-12-01");
        int employeeId = 3;
        try {
            List<LocalTime> schedules = ServiceFactory
                    .getInstance()
                    .getScheduleService()
                    .schedulesByEmployeeDate(employeeId, localDate);
            assertEquals(schedules.size(),2);
        } catch (ServiceException e) {
            LOGGER.error(String.format("it is impossible to get schedules " +
                    "by employee with id: %d",employeeId),e);
        }
    }

    @Test(priority = 2)
    public void testAddschedule() {
        LocalDate date = LocalDate.parse("2021-12-02");
        int employeeId = 2;
        try {
            ServiceFactory
                    .getInstance()
                    .getScheduleService()
                    .addSchedule(employeeId, date);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                ScheduleDao scheduleDao = new ScheduleDaoImpl();
                scheduleDao.setConnection(connection);
                List<Schedule> actual  = scheduleDao.findByEmployee(3);
                assertEquals(actual.size(),2);
                connection.close();
            } catch (DaoException e) {LOGGER.error(e);}
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to add schedule",e);
        }
    }

    @Test(priority = 3)
    public void testDeleteschedule() {
        try {
            ServiceFactory
                    .getInstance()
                    .getScheduleService()
                    .deleteschedule(1);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                ScheduleDao scheduleDao = new ScheduleDaoImpl();
                scheduleDao.setConnection(connection);
                Schedule schedule = scheduleDao.findById(1);
                assertNull(schedule);
                connection.close();
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting schedule");
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to delete schedule");
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
            ConnectionPoolService connectionPoolService = new ConnectionPoolServiceImpl();
            connectionPoolService.destroy();
        } catch (SQLException |DaoException|IOException e) {LOGGER.error(e);}
    }
}