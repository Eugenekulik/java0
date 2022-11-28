package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.*;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import by.training.beauty.dto.AppointmentDto;
import by.training.beauty.dto.ProcedureDto;
import by.training.beauty.dto.ScheduleDto;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.AdministrateService;
import by.training.beauty.service.spec.ConnectionPoolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class AdministrateServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateServiceImplTest.class);
    TransactionFactory transactionFactory;
    @BeforeClass
    public void init() {
        transactionFactory = mock(TransactionFactoryImpl.class);
        Transaction transaction = mock(TransactionImpl.class);
        UserDao userDao = mock(UserDaoImpl.class);
        ScheduleDao scheduleDao = mock(ScheduleDaoImpl.class);
        ProcedureDao procedureDao = mock(ProcedureDaoImpl.class);
        AppointmentDao appointmentDao = mock(AppointmentDaoImpl.class);
        RoleDao roleDao = mock(RoleDaoImpl.class);
        CategoryDao categoryDao = mock(CategoryDaoImpl.class);
        ProcedureEmployeeDao procedureEmployeeDao = mock(ProcedureEmployeeDaoImpl.class);
        try {
            when(roleDao.findByUser(anyInt()))
                    .thenReturn(List.of(new Role(2,"client")));
            when(userDao.findInterval(anyInt(),10))
                    .thenReturn(List.of(new User.Builder()
                            .setName("name")
                            .setId(1)
                            .setLogin("login")
                            .setPassword("password")
                            .setPhone("+375290000000").build()));
            when(appointmentDao.findInterval(0,10))
                    .thenReturn(List.of(new Appointment.Builder()
                            .setId(1)
                            .setStatus(1)
                            .setUserId(1)
                            .setProcedureEmployeeId(1)
                            .setPrice(30)
                            .build()));
            when(scheduleDao.count()).thenReturn(45);
            when(userDao.count()).thenReturn(45);
            when(appointmentDao.count()).thenReturn(45);
            when(procedureDao.count()).thenReturn(45);
            when(transaction.createDao(DaoEnum.CATEGORY.getDao())).thenReturn(categoryDao);
            when(transaction.createDao(DaoEnum.ROLE.getDao())).thenReturn(roleDao);
            when(transaction.createDao(DaoEnum.USER.getDao())).thenReturn(userDao);
            when(transaction.createDao(DaoEnum.PROCEDURE.getDao())).thenReturn(procedureDao);
            when(transaction.createDao(DaoEnum.APPOINTMENT.getDao())).thenReturn(appointmentDao);
            when(transaction.createDao(DaoEnum.SCHEDULE.getDao())).thenReturn(scheduleDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (DaoException e) {
            e.printStackTrace();
        }
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
        AdministrateService administrateService = new AdministrateServiceImpl(transactionFactory);
        try {
            int[] actual = {administrateService.getPagecount(1),
                    administrateService.getPagecount(2),
                    administrateService.getPagecount(3),
                    administrateService.getPagecount(4),};
            int[] expected = {5, 5, 5, 5};
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get pageCount");
        }
    }

    @Test
    public void testAdministrateUsers() {
        try {
            List<User> users = new AdministrateServiceImpl(transactionFactory)
                    .administrateUsers(1);
            Assertions.assertThat(users)
                    .usingRecursiveFieldByFieldElementComparatorIgnoringFields("roles")
                    .contains(new User.Builder()
                            .setName("name")
                            .setId(1)
                            .setLogin("login")
                            .setPassword("password")
                            .setPhone("+375290000000")
                            .build())
                    .size().isEqualTo(1);
            Assertions.assertThat(users.get(0).getRoles())
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new Role(2,"client"));
        } catch (ServiceException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
        ConnectionPoolService connectionPoolService = new ConnectionPoolServiceImpl();
        connectionPoolService.destroy();
    }
}