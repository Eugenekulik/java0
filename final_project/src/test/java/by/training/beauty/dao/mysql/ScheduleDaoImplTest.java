package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.RoleDao;
import by.training.beauty.dao.spec.ScheduleDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.Schedule;
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

import static org.testng.Assert.*;

public class ScheduleDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleDaoImplTest.class);


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
    public void testCount() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.count()).isEqualTo(3);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test(priority = 1)
    public void testCreate_Return_Schedule() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.create(new Schedule.Builder()
                    .setEmployeeId(3)
                    .setDate(LocalDateTime.parse("2022-11-28 10:00:00",
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build())).isNotNull();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testDelete_Return_False() throws DaoException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.delete(4)).isFalse();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test(priority = 2)
    public void testDelete_Return_True() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.delete(3)).isTrue();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindAll() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.findall().size()).isEqualTo(3);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindById_Return_Schedule() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.findById(1))
                    .usingRecursiveComparison()
                    .isEqualTo(new Schedule.Builder()
                            .setId(1)
                            .setAppointmentId(1)
                            .setEmployeeId(3)
                            .setDate(LocalDateTime.parse("2021-12-01 10:00:00",
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .build());
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindById_Return_Null() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.findById(4)).isNull();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindByEmployee() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.findByEmployee(3))
                    .usingRecursiveFieldByFieldElementComparator()
                    .doesNotContain(new Schedule.Builder()
                            .setId(1)
                            .setEmployeeId(3)
                            .setAppointmentId(1)
                            .setDate(LocalDateTime.parse("2021-12-01 10:00:00",
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .build())
                    .contains(new Schedule.Builder()
                            .setId(2)
                            .setEmployeeId(3)
                            .setDate(LocalDateTime.parse("2021-12-01 13:00:00",
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .build())
                    .size().isEqualTo(2);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindByEmployeeDate() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.findByEmployeeDate(
                    LocalDateTime.parse("2021-12-01 10:00:00",
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    3)).isNotNull();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindInterval() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.findInterval(1, 2))
                    .usingRecursiveFieldByFieldElementComparatorIgnoringFields("date")
                    .doesNotContain(new Schedule.Builder()
                            .setId(1)
                            .setEmployeeId(3)
                            .setAppointmentId(1)
                            .build()
                    )
                    .size().isEqualTo(2);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test(priority = 3)
    public void testUpdate() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            Assertions.assertThat(scheduleDao.update(new Schedule.Builder()
                    .setId(1)
                    .setEmployeeId(3)
                    .setDate(LocalDateTime.parse("2022-11-11 10:00:00",
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build())).isTrue();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
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