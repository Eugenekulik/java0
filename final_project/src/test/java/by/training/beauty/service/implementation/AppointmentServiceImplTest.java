package by.training.beauty.service.implementation;

import by.training.beauty.dao.mysql.*;
import by.training.beauty.dao.spec.*;
import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.domain.*;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.AppointmentService;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class AppointmentServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentServiceImplTest.class);
    private TransactionFactory transactionFactory;
    @BeforeClass
    public void init(){
        transactionFactory = mock(TransactionFactory.class);
        Transaction transaction = mock(Transaction.class);
        UserDao userDao = mock(UserDao.class);
        AppointmentDao appointmentDao = mock(AppointmentDao.class);
        ScheduleDao scheduleDao = mock(ScheduleDao.class);
        ProcedureDao procedureDao = mock(ProcedureDao.class);
        try{
//Configure mock appointmentDao
            when(appointmentDao.create(new Appointment.Builder()
                    .setUserId(1)
                    .setPrice(30)
                    .setStatus(1)
                    .build()))
                    .thenReturn(new Appointment.Builder()
                            .setId(1)
                            .setUserId(1)
                            .setPrice(30)
                            .setStatus(1)
                            .build());
            when(appointmentDao.cancel(1)).thenReturn(true);
            when(appointmentDao.getUserAppointments(1))
                    .thenReturn(List.of(new Appointment.Builder()
                            .setId(1)
                            .setPrice(20)
                            .setStatus(1)
                            .setUserId(1)
                            .build()));
            when(appointmentDao.update(any())).thenReturn(true);
            doNothing().when(appointmentDao).archive();


//Configure mock scheduleDao
            when(scheduleDao.findByEmployeeDate(any(),eq(1)))
                    .thenReturn(new Schedule.Builder()
                            .setId(1)
                            .setAppointmentId(1)
                            .setEmployeeId(2)
                            .build());
            when(scheduleDao.update(any())).thenReturn(true);


//Configure mock scheduleDao
            when(userDao.findEmployeeByAppointment(1))
                    .thenReturn(new User.Builder()
                            .setId(2)
                            .setName("employee")
                            .setLogin("employee")
                            .setPassword("password")
                            .setPhone("+375291111111")
                            .build());
            when(procedureDao.findByAppointment(1))
                    .thenReturn(new Procedure.Builder()
                            .setId(1)
                            .setCategoryId(1)
                            .setElapsedTime(60)
                            .setName("procedure")
                            .setDescription("description")
                            .build());

            when(transaction.createDao(DaoEnum.SCHEDULE.getDao())).thenReturn(scheduleDao);
            when(transaction.createDao(DaoEnum.USER.getDao())).thenReturn(userDao);
            when(transaction.createDao(DaoEnum.APPOINTMENT.getDao())).thenReturn(appointmentDao);
            when(transaction.createDao(DaoEnum.PROCEDURE.getDao())).thenReturn(procedureDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testAddAppointment() {
        Appointment expected = new Appointment.Builder()
                .setId(1)
                .setUserId(1)
                .setPrice(30)
                .setStatus(1)
                .build();
        AppointmentService appointmentService = new AppointmentServiceImpl(transactionFactory);
        try{
            Assertions.assertThat(appointmentService
                    .addAppointment(expected,1,2))
                    .isTrue();
        } catch (ServiceException e){
            e.printStackTrace();
        }

    }

    @Test
    public void testCancelAppointment() {
        try {
            AppointmentService appointmentService = new AppointmentServiceImpl(transactionFactory);
            Assertions.assertThat(appointmentService.cancelAppointment(1)).isTrue();
        } catch (ServiceException e) {e.printStackTrace();}
    }


    @Test
    public void testUsersAppointment() {
        try {
            Appointment expected = new Appointment.Builder()
                    .setId(1)
                    .setPrice(20)
                    .setStatus(1)
                    .setUserId(1)
                    .setEmployee(new User.Builder()
                            .setId(2)
                            .setName("employee")
                            .setLogin("employee")
                            .setPassword("password")
                            .setPhone("+375291111111")
                            .build())
                    .setProcedure(new Procedure.Builder()
                            .setId(1)
                            .setCategoryId(1)
                            .setElapsedTime(60)
                            .setName("procedure")
                            .setDescription("description")
                            .build())
                    .build();
            AppointmentService appointmentService = new AppointmentServiceImpl(transactionFactory);
            List<Appointment> appointments = appointmentService.usersAppointment(1);
            Assertions.assertThat(appointments)
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(expected)
                    .size().isEqualTo(1);
        } catch (ServiceException e){
            e.printStackTrace();
        }
    }

   @Test
    public void testUpdateAppointment(){
        try{
            AppointmentService appointmentService = new AppointmentServiceImpl(transactionFactory);
            Assertions.assertThat(appointmentService.updateAppointment(new Appointment())).isTrue();
        } catch (ServiceException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testArchive(){
        AppointmentService appointmentService = new AppointmentServiceImpl(transactionFactory);
        appointmentService.archive();
        try {
            verify(((AppointmentDao) transactionFactory.createTransaction().createDao(DaoEnum.APPOINTMENT.getDao())),times(1)).archive();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void destroy(){
    }


}