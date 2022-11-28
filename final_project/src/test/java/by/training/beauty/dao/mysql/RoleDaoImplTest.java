package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.RoleDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.domain.Role;
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

public class RoleDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImplTest.class);


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
    public void testCount(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.count()).isEqualTo(3);
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test(priority = 1)
    public void testCreate_Return_ROLE(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.create(new Role("unknown"))).isNotNull();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test(expectedExceptions = DaoException.class)
    public void testCreate_Return_Throw_DaoException() throws DaoException {
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            roleDao.create(new Role("admin"));
            transaction.commit();
        } finally {
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testDelete_Return_Null() throws DaoException {
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.delete(4)).isFalse();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test(priority = 2)
    public void testDelete_Return_True(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.delete(3)).isTrue();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindAll(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.findall().size()).isEqualTo(3);
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindById_Return_ROLE(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.findById(1))
                    .usingRecursiveComparison()
                    .isEqualTo(new Role(1,"admin"));
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindById_Return_Null(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao RoleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(RoleDao.findById(4)).isNull();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testFindByUser_Return_ROLE(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.findByUser(3))
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new Role(3,"employee"));
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindByUser_Return_Null(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.findByUser(4)).isEmpty();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }


    @Test
    public void testFindInterval(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            List<Role> roles = roleDao.findInterval(1,2);
            Assertions.assertThat(roles).size().isEqualTo(2);
            Assertions.assertThat(roles)
                    .usingRecursiveFieldByFieldElementComparator()
                    .doesNotContain(new Role(1,"admin"));
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }


    @Test(priority = 3)
    public void testUpdate(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.update(new Role(1,"administrator"))).isTrue();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test(priority = 3)
    public void testUpdateRolesForUser(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            Assertions.assertThat(roleDao.updateRolesForUser(new User.Builder()
                    .setId(1)
                    .addRole(new Role(3,"employee"))
                    .addRole(new Role(1,"admin"))
                    .build())).isTrue();
            transaction.commit();
        } catch (DaoException e){
            e.printStackTrace();
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
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