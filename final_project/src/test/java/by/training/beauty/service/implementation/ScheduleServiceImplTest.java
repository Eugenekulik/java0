package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.ScheduleDaoImplTest;
import by.training.beauty.dao.spec.ScheduleDao;
import by.training.beauty.dao.mysql.ScheduleDaoImpl;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Schedule;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.ConnectionPoolService;
import by.training.beauty.service.spec.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class ScheduleServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleServiceImplTest.class);
    TransactionFactory transactionFactory;

    @BeforeClass
    public void init() {
        transactionFactory = mock(TransactionFactory.class);
        Transaction transaction = mock(Transaction.class);
        ScheduleDao scheduleDao = mock(ScheduleDao.class);
        try {
// Configure mock scheduleDao
            when(scheduleDao.findByEmployee(2))
                    .thenReturn(List.of(new Schedule.Builder()
                            .setId(1)
                            .setEmployeeId(2)
                            .setAppointmentId(1)
                            .setDate(LocalDateTime.parse("2021-12-01 10:00:00",
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .build()));
            when(scheduleDao.create(any())).thenReturn(new Schedule());
            when(scheduleDao.delete(anyInt())).thenReturn(true);
            when(scheduleDao.archive()).thenReturn(true);

//Configure mock transaction
            when(transaction.createDao(DaoEnum.SCHEDULE.getDao())).thenReturn(scheduleDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSchedulesByEmployeeDate() {
        try {
            ScheduleService scheduleService = new ScheduleServiceImpl(transactionFactory);
            Assertions.assertThat(scheduleService
                            .schedulesByEmployeeDate(2, LocalDate.parse("2021-12-01",
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                    .contains(LocalTime.parse("10:00:00",
                            DateTimeFormatter.ofPattern("HH:mm:ss")))
                    .size().isEqualTo(1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddschedule() {
        try {
            ScheduleService scheduleService = new ScheduleServiceImpl(transactionFactory);
            Assertions.assertThat(scheduleService.addSchedule(2,
                    LocalDate.parse("2021-12-01", DateTimeFormatter.ISO_LOCAL_DATE))).isTrue();
            verify(((ScheduleDao) transactionFactory
                    .createTransaction()
                    .createDao(DaoEnum.SCHEDULE.getDao())), times(9)).create(any());
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteSchedule() {
        try {
            ScheduleService scheduleService = new ScheduleServiceImpl(transactionFactory);
            Assertions.assertThat(scheduleService.deleteSchedule(1)).isTrue();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testArchive() {
        ScheduleService scheduleService = new ScheduleServiceImpl(transactionFactory);
        Assertions.assertThat(scheduleService.archive()).isTrue();
    }

    @AfterClass
    public void destroy() {
    }
}