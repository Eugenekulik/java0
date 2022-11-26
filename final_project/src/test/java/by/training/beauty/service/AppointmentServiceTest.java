package by.training.beauty.service;

import by.training.beauty.dao.spec.AppointmentDao;
import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.AppointmentDaoImpl;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Entity;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.User;
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

public class AppointmentServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentServiceTest.class);
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

    @Test(priority = 3)
    public void testAddAppointment() {
        AppointmentService appointmentService = new AppointmentService();
        Appointment expected = new Appointment();
        expected.setUserId(2);
        expected.setStatus(1);
        expected.setPrice(20.0);
        expected.setDate(LocalDateTime.of(LocalDate.parse("2021-12-01"), LocalTime.parse("10:00:00")));
        expected.setProcedureEmployeeId(2);
        Procedure procedure = new Procedure();
        procedure.setId(1);
        User employee = new User();
        try {
            appointmentService.addAppointment(expected, procedure.getId(), 3);
        } catch (ServiceException e) {LOGGER.error("it is impossible to delete user");}
        try {
            PooledConnection connection = ConnectionPool.getInstance().getConnection();
            AppointmentDao appointmentDao = new AppointmentDaoImpl();
            appointmentDao.setConnection(connection);
            List<Appointment> appointments  = appointmentDao.findall();
            Appointment actual  = appointments.stream()
                    .filter(appointment -> appointment.getDate().equals(expected.getDate())).findFirst().get();
            assertNotNull(actual);
        } catch (DaoException e) {LOGGER.error(e);}
    }

    @Test(priority = 2)
    public void testCancelAppointment() {
        AppointmentService appointmentService = new AppointmentService();
        try {
            appointmentService.cancelAppointment(1);
        } catch (ServiceException e) {LOGGER.error("it is impossible to delete user");}
        try {
            PooledConnection connection = ConnectionPool.getInstance().getConnection();
            AppointmentDao appointmentDao = new AppointmentDaoImpl();
            appointmentDao.setConnection(connection);
            Appointment actual  = appointmentDao.findById(1);
            assertEquals(actual.getStatus(),4);
        } catch (DaoException e) {LOGGER.error(e);}
    }
    @Test(priority = 1)
    public void testUsersAppointment() {
        AppointmentService appointmentService = new AppointmentService();
        User user = new User();
        user.setId(3);
        try {
            List<Entity> entities = appointmentService.usersAppointment(user);
            assertEquals(entities.size(), 7);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get user's appointments");
        }
    }

    @Test
    public void testUpdateAppointment(){
        AppointmentService appointmentService = new AppointmentService();
        Appointment expected = new Appointment();
        expected.setId(1);
        expected.setProcedureEmployeeId(2);
        expected.setStatus(2);
        expected.setPrice(30.0);
        expected.setDate(LocalDateTime.of(2021,12,1,9,0,0));
        try {
            appointmentService.updateAppointment(expected);
            try {
                PooledConnection connection = ConnectionPool.getInstance().getConnection();
                AppointmentDao appointmentDao = new AppointmentDaoImpl();
                appointmentDao.setConnection(connection);
                Appointment actual = appointmentDao.findById(1);
                assertEquals(actual.getStatus(),expected.getStatus());
            } catch (DaoException e) {
                LOGGER.error("an error occurred while getting appointment");
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to update appointment",e);
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