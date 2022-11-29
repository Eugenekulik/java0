package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.AppointmentDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.User;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

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
        } catch (DaoException e) {
            LOGGER.error("It is impossible to init connection pool to database", e);
        }
    }


    @Test
    public void testFindAll() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.findall();
            Assertions.assertThat(appointments).isNotEmpty();
            Assertions.assertThat(appointments.size()).isEqualTo(3);
            Assertions.assertThat(appointments).usingRecursiveFieldByFieldElementComparator().contains(new Appointment.Builder()
                    .setId(1)
                    .setUserId(2)
                    .setDate(LocalDateTime.parse("2021-12-01 09:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setStatus(1)
                    .setPrice(50)
                    .build());
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFindByID_Return_True() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment actual = appointmentDao.findById(1);
            Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(new Appointment.Builder()
                    .setId(1)
                    .setUserId(2)
                    .setDate(LocalDateTime.parse("2021-12-01 09:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setStatus(1)
                    .setPrice(50)
                    .build());
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFindById_Return_Null() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment actual = appointmentDao.findById(4);
            Assertions.assertThat(actual).isNull();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFindInterval() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.findInterval(1, 2);
            Assertions.assertThat(appointments.size()).isEqualTo(2);
            Assertions.assertThat(appointments).usingRecursiveFieldByFieldElementComparator()
                    .doesNotContain(new Appointment.Builder()
                            .setId(1)
                            .setUserId(3)
                            .setDate(LocalDateTime.parse("2021-12-01 09:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .setStatus(1)
                            .setPrice(50)
                            .build());
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testUpdate_Return_True() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment expected = new Appointment.Builder()
                    .setId(1)
                    .setUserId(3)
                    .setDate(LocalDateTime.parse("2021-12-01 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setStatus(3)
                    .setPrice(40)
                    .build();

            Assertions.assertThat(appointmentDao.update(expected)).isTrue();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCancel_Return_True() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Assertions.assertThat(appointmentDao.cancel(3)).isTrue();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCancel_Return_False() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Assertions.assertThat(appointmentDao.cancel(4)).isFalse();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetEmployeeAppointments_Return_Appointments() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.getEmployeeAppointments(3);
            Assertions.assertThat(appointments.size()).isEqualTo(3);
            Assertions.assertThat(appointments)
                    .usingRecursiveFieldByFieldElementComparatorIgnoringFields("procedure","employee")
                    .contains(new Appointment.Builder()
                            .setId(2)
                            .setUserId(2)
                            .setDate(LocalDateTime.parse("2021-12-01 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .setStatus(1)
                            .setPrice(30)
                            .build());
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetEmployeeAppointments_Return_Empty_List() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.getEmployeeAppointments(2);
            Assertions.assertThat(appointments).isEmpty();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testGetUserAppointment_Return_Appointment() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.getUserAppointments(2);
            Assertions.assertThat(appointments.size()).isEqualTo(3);
            Assertions.assertThat(appointments).usingRecursiveFieldByFieldElementComparator()
                    .contains(new Appointment.Builder()
                            .setId(2)
                            .setUserId(2)
                            .setDate(LocalDateTime.parse("2021-12-01 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .setStatus(1)
                            .setPrice(30)
                            .build());
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetUserAppointments_Return_Empty_List() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            List<Appointment> appointments = appointmentDao.getUserAppointments(4);
            Assertions.assertThat(appointments).isEmpty();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test(priority = 1)
    public void testDelete_Return_True() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Assertions.assertThat(appointmentDao.delete(2)).isTrue();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDelete_Return_False() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Assertions.assertThat(appointmentDao.delete(4)).isFalse();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @Test(priority = 2)
    public void testCreate_Return_Appointment() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment expected = new Appointment.Builder()
                    .setUserId(3)
                    .setDate(LocalDateTime.parse("2021-12-01 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setStatus(1)
                    .setPrice(30)
                    .setProcedure(new Procedure.Builder().setId(1).build())
                    .setEmployee(new User.Builder().setId(3).build())
                    .build();
            Appointment actual = appointmentDao.create(expected);
            transaction.commit();
            Assertions.assertThat(actual)
                    .usingRecursiveComparison().ignoringFields("id","procedure","employee")
                    .isEqualTo(expected);
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testCount(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Assertions.assertThat(appointmentDao.count()).isEqualTo(3);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void destroy() {
        try {
            URL resource = getClass().getClassLoader().getResource("init_test.sql");
            Scanner scanner = new Scanner(new FileReader(resource.getFile()));
            scanner.useDelimiter(";");
            List<String> queries = new ArrayList<>();
            while (scanner.hasNext()) {
                queries.add(scanner.next() + ";");
            }
            PooledConnection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            for (String s : queries) {
                statement.executeUpdate(s);
            }
            ConnectionPool.getInstance().destroy();
        } catch (SQLException | DaoException | IOException e) {
            e.printStackTrace();
        }
    }

}