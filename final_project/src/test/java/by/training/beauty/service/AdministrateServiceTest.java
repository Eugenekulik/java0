package by.training.beauty.service;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.spec.ScheduleDao;
import by.training.beauty.domain.Entity;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.User;
import by.training.beauty.dto.AppointmentDto;
import by.training.beauty.dto.ProcedureDto;
import by.training.beauty.dto.ScheduleDto;
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

public class AdministrateServiceTest {
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
    public void testGetPageCount() {
        AdministrateService administrateService =
                ServiceFactory.getInstance().getAdministrateService();
        try {
            int[] actual = {administrateService.getPagecount(1),
                    administrateService.getPagecount(2),
                    administrateService.getPagecount(3),
                    administrateService.getPagecount(4),};
            int[] expected = {1, 1, 1, 1};
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get pageCount");
        }
    }

    @Test
    public void testAdministrateUsers() {
        try {
            List<User> users = ServiceFactory
                    .getInstance()
                    .getAdministrateService()
                    .administrateUsers(1);
            assertEquals(users.size(), 3);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get user list");
        }
    }
    @Test
    public void testAdministrateAppointments() {
        try {
            List<AppointmentDto> appointmentDtoList = ServiceFactory
                    .getInstance()
                    .getAdministrateService()
                    .administrateAppointments(1);
            assertEquals(appointmentDtoList.size(), 3);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get user list");
        }
    }

    @Test
    public void testAdministrateProcedures() {
        try {
            List<ProcedureDto> procedureDtoList = ServiceFactory
                    .getInstance()
                    .getAdministrateService()
                    .administrateProcedures(1);
            assertEquals(procedureDtoList.size(), 6);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get user list");
        }
    }

    @Test
    public void testAdministrateSchedules() {
        try {
            List<ScheduleDto> scheduleDtoList = ServiceFactory
                    .getInstance()
                    .getAdministrateService()
                    .administrateSchedules(1);
            assertEquals(scheduleDtoList.size(), 3);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get user list");
        }
    }

    @AfterClass
    public void destroy(){
        ConnectionPoolService connectionPoolService = new ConnectionPoolService();
        connectionPoolService.destroy();
    }
}