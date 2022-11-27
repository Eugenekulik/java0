package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.UserDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.testng.Assert.*;

public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImplTest.class);


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
    public void testCount()  {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            Assertions.assertThat(userDao.count()).isEqualTo(3);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test(priority = 2)
    public void testCreate_Return_User() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            User user = new User.Builder()
                    .setName("newUser")
                    .setLogin("newUser")
                    .setPassword("newUser")
                    .setPhone("+375295487548")
                    .build();
            Assertions.assertThat(userDao.create(user))
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(user);
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test(expectedExceptions = DaoException.class)
    public void testCreate_Throw_DaoException()throws DaoException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            User user = new User.Builder()
                    .setName("newUser")
                    .setLogin("firstUser")
                    .setPassword("newUser")
                    .setPhone("+375295487548")
                    .build();
            Assertions.assertThat(userDao.create(user)).isNull();
            transaction.commit();
        } finally {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test(priority = 1)
    public void testDelete_Return_True(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            Assertions.assertThat(userDao.delete(3)).isTrue();
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testDelete_Return_false(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            Assertions.assertThat(userDao.delete(4)).isFalse();
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindAll(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            List<User> users = userDao.findall();
            Assertions.assertThat(users.size()).isEqualTo(3);
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindById_Return_User(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());

            Assertions.assertThat(userDao.findById(1))
                    .usingRecursiveComparison()
                            .isEqualTo(new User.Builder()
                                    .setId(1)
                                    .setLogin("firstuser")
                                    .setPassword("bc45d36ca604cfbaf323636e79cf720e524bd8b5a34094763cd7e0c70a1d08a8")
                                    .setName("firstuser")
                                    .setPhone("+375291111111")
                                    .build());
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindById_Return_Null(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());

            Assertions.assertThat(userDao.findById(4)).isNull();
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindByLogin_Return_User(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());

            Assertions.assertThat(userDao.findByLogin("firstuser"))
                    .usingRecursiveComparison()
                    .isEqualTo(new User.Builder()
                            .setId(1)
                            .setLogin("firstuser")
                            .setPassword("bc45d36ca604cfbaf323636e79cf720e524bd8b5a34094763cd7e0c70a1d08a8")
                            .setName("firstuser")
                            .setPhone("+375291111111")
                            .build());
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindByLogin_Return_Null(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());

            Assertions.assertThat(userDao.findByLogin("fourthuser")).isNull();
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindByName_Return_User(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());

            Assertions.assertThat(userDao.findByName("firstuser"))
                    .usingRecursiveComparison()
                    .isEqualTo(new User.Builder()
                            .setId(1)
                            .setLogin("firstuser")
                            .setPassword("bc45d36ca604cfbaf323636e79cf720e524bd8b5a34094763cd7e0c70a1d08a8")
                            .setName("firstuser")
                            .setPhone("+375291111111")
                            .build());
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindByName_Return_Null(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());

            Assertions.assertThat(userDao.findByName("fourthuser")).isNull();
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindEmployyes(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());

            Assertions.assertThat(userDao.findEmployees())
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new User.Builder()
                            .setId(3)
                            .setLogin("thirduser")
                            .setPassword("b816140000cb14150c1fdb98c83edadc3fc8fe4dc43647312559be8b202fb1be")
                            .setName("thirduser")
                            .setPhone("+375293333333")
                            .build());
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindInterval(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            List<User> users = userDao.findInterval(1,2);
            Assertions.assertThat(users.size()).isEqualTo(2);
            Assertions.assertThat(users).usingRecursiveFieldByFieldElementComparator()
                            .contains(new User.Builder()
                                    .setId(3)
                                    .setLogin("thirduser")
                                    .setPassword("b816140000cb14150c1fdb98c83edadc3fc8fe4dc43647312559be8b202fb1be")
                                    .setName("thirduser")
                                    .setPhone("+375293333333")
                                    .build());
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test(priority = 3)
    public void testUpdate_Return_True(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            Assertions.assertThat(userDao.update(new User.Builder()
                    .setId(3)
                    .setLogin("thirduser")
                    .setPassword("b816140000cb14150c1fdb98c83edadc3fc8fe4dc43647312559be8b202fb1be")
                    .setName("anotherName")
                    .setPhone("+375294444444")
                    .build())).isTrue();
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testUpdate_Return_False(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            Assertions.assertThat(userDao.update(new User.Builder()
                    .setId(4)
                    .setLogin("thirduser")
                    .setPassword("b816140000cb14150c1fdb98c83edadc3fc8fe4dc43647312559be8b202fb1be")
                    .setName("thirduser")
                    .setPhone("+375293333333")
                    .build())).isFalse();
            Assertions.assertThat(userDao.update(new User.Builder()
                    .setId(3)
                    .setLogin("firstuser")
                    .setPassword("b816140000cb14150c1fdb98c83edadc3fc8fe4dc43647312559be8b202fb1be")
                    .setName("thirduser")
                    .setPhone("+375293333333")
                    .build())).isFalse();
            transaction.commit();
        } catch (DaoException e){
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
        }
    }


    @AfterClass
    public void destroy(){
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