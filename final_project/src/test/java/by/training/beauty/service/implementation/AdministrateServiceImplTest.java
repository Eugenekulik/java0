package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.*;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.*;
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
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
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
        try {

//Configure mock roleDao
            when(roleDao.findByUser(anyInt()))
                    .thenReturn(List.of(new Role(2,"client")));

//Configure mock procedureDao
            when(procedureDao.count()).thenReturn(45);
            when(procedureDao.findByAppointment(1))
                    .thenReturn(new Procedure.Builder()
                            .setId(1).setName("name")
                            .setDescription("description")
                            .setCategoryId(1)
                            .setElapsedTime(60)
                            .build());
            when(procedureDao.findInterval(0,10))
                    .thenReturn(List.of(new Procedure.Builder()
                            .setId(1).setName("name")
                            .setDescription("description")
                            .setCategoryId(1)
                            .setElapsedTime(60)
                            .build()));


//Configure mock userDao
            when(userDao.count()).thenReturn(45);
            when(userDao.findInterval(anyInt(),eq(10)))
                    .thenReturn(List.of(new User.Builder()
                            .setName("client")
                            .setId(1)
                            .setLogin("client")
                            .setPassword("password")
                            .setPhone("+375290000000").build()));
            when(userDao.findEmployeeByAppointment(1))
                    .thenReturn(new User.Builder()
                            .setId(2)
                            .setLogin("employee")
                            .setPassword("password")
                            .setName("employee")
                            .setPhone("+375291111111")
                            .build());
            when(userDao.findEmployees())
                    .thenReturn(List.of(new User.Builder()
                            .setId(2)
                            .setLogin("employee")
                            .setPassword("password")
                            .setName("employee")
                            .setPhone("+375291111111")
                            .build()));


//Configure mock appointmentDao
            when(appointmentDao.count()).thenReturn(45);
            when(appointmentDao.findInterval(0,10))
                    .thenReturn(List.of(new Appointment.Builder()
                            .setId(1)
                            .setStatus(1)
                            .setUserId(1)
                            .setPrice(30)
                            .build()));


//Configure mock scheduleDao
            when(scheduleDao.count()).thenReturn(45);
            when(scheduleDao.findInterval(0,10))
                    .thenReturn(List.of(new Schedule.Builder()
                            .setId(1)
                            .setAppointmentId(1)
                            .setEmployeeId(2)
                            .build()));


//Configure mock categoryDao
            when(categoryDao.findById(1))
                    .thenReturn(new Category.Builder()
                            .setId(1)
                            .setName("category")
                            .setDescription("description")
                            .build());


//Configure mock transaction
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
                            .setName("client")
                            .setId(1)
                            .setLogin("client")
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
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setId(1);
            appointmentDto.setProcedure("name");
            appointmentDto.setEmployee("employee");
            appointmentDto.setStatus(1);
            appointmentDto.setPrice(30);
            appointmentDto.setClient("" + 1);
            List<AppointmentDto> appointmentDtoList = new AdministrateServiceImpl(transactionFactory)
                    .administrateAppointments(1);
            Assertions.assertThat(appointmentDtoList)
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(appointmentDto)
                    .size().isEqualTo(1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdministrateProcedures() {
        try {
            ProcedureDto procedureDto = new ProcedureDto();
            procedureDto.setId(1);
            procedureDto.setCategory("category");
            procedureDto.setName("name");
            procedureDto.setDescription("description");
            procedureDto.setElapsedTime(60);
            List<ProcedureDto> procedureDtoList = new AdministrateServiceImpl(transactionFactory)
                    .administrateProcedures(1);
            Assertions.assertThat(procedureDtoList)
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(procedureDto)
                    .size().isEqualTo(1);
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
    }
}