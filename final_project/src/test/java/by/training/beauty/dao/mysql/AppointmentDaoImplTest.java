package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.AppointmentDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class AppointmentDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentDaoImplTest.class);


    TransactionFactoryImpl transactionFactory;

    @BeforeClass
    public void init() {
        Properties properties = new Properties();
        try {
            URL resource = getClass().getClassLoader().getResource("connection.properties");
            properties.load(new FileReader(new File(resource.toURI())));
            ConnectionPool.getInstance().init(properties.getProperty("db.driver"), properties.getProperty("db.url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"), 1, 4, 30);
            transactionFactory = new TransactionFactoryImpl();
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("It is impossible to load properties", e);
        } catch (
                DaoException e) {
            LOGGER.error("It is impossible to init connection pool to database", e);
        }
    }


    @Test
    public void testFindAll(){
        try {
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.findall();
            Assertions.assertThat(appointments).isNotEmpty();
            Assertions.assertThat(appointments.size()).isEqualTo(3);
            Assertions.assertThat(appointments).usingRecursiveFieldByFieldElementComparator().contains(new Appointment.Builder()
                    .setId(1)
                    .setUserId(3)
                    .setProcedureEmployeeId(2)
                    .setDate(LocalDateTime.parse("2021-12-01 09:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setStatus(1)
                    .setPrice(50)
                    .build());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByID_Return_True(){
        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment actual = appointmentDao.findById(1);
            Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(new Appointment.Builder()
                    .setId(1)
                    .setUserId(3)
                    .setProcedureEmployeeId(2)
                    .setDate(LocalDateTime.parse("2021-12-01 09:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setStatus(1)
                    .setPrice(50)
                    .build());
        } catch (DaoException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById_Return_Null(){
        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment actual = appointmentDao.findById(4);
            Assertions.assertThat(actual).isNull();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFindInterval(){

        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.findInterval(1,2);
            Assertions.assertThat(appointments.size()).isEqualTo(2);
            Assertions.assertThat(appointments).usingRecursiveFieldByFieldElementComparator()
                    .doesNotContain(new Appointment.Builder()
                            .setId(1)
                            .setUserId(3)
                            .setProcedureEmployeeId(2)
                            .setDate(LocalDateTime.parse("2021-12-01 09:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .setStatus(1)
                            .setPrice(50)
                            .build());
        } catch (DaoException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate_Return_True(){
        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment expected =new Appointment.Builder()
                    .setId(1)
                    .setUserId(3)
                    .setProcedureEmployeeId(2)
                    .setDate(LocalDateTime.parse("2021-12-01 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setStatus(3)
                    .setPrice(40)
                    .build();

            Assertions.assertThat(appointmentDao.update(expected)).isTrue();
        } catch (DaoException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testCancel_Return_True(){
        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Assertions.assertThat(appointmentDao.cancel(3)).isTrue();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCancel_Return_False(){
        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Assertions.assertThat(appointmentDao.cancel(4)).isFalse();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetEmployeeAppointments_Return_One_Appointment(){
        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.getEmployeeAppointments(1);
            Assertions.assertThat(appointments.size()).isEqualTo(1);
            Assertions.assertThat(appointments).usingRecursiveFieldByFieldElementComparator()
                            .contains(new Appointment.Builder()
                                    .setId(2)
                                    .setUserId(3)
                                    .setProcedureEmployeeId(1)
                                    .setDate(LocalDateTime.parse("2021-12-01 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .setStatus(1)
                                    .setPrice(30)
                                    .build());
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetEmployeeAppointments_Return_Empty_List(){
        try{
            Transaction transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.getEmployeeAppointments(3);
            Assertions.assertThat(appointments).isEmpty();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public void destroy(){
        ConnectionPool.getInstance().destroy();
    }

}