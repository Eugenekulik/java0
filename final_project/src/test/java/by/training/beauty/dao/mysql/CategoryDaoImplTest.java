package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.CategoryDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.domain.Category;
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


public class CategoryDaoImplTest {


    private static final Logger LOGGER = LogManager.getLogger(CategoryDaoImplTest.class);


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
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.count()).isEqualTo(7);
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
    public void testCreate_Return_Category(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.create(new Category.Builder()
                    .setName("newCategory")
                    .setDescription("newCategory")
                    .build())).isNotNull();
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
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            categoryDao.create(new Category.Builder()
                    .setName("Аппаратная косметология")
                    .setDescription("newCategory")
                    .build());
            transaction.commit();
        } finally {
            try{
                if(transaction != null) transaction.rollback();
            } catch (DaoException e1){
                e1.printStackTrace();
            }
        }
    }

    @Test(expectedExceptions = DaoException.class)
    public void testDelete_Throw_DaoException() throws DaoException {
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            categoryDao.delete(6);
            transaction.commit();
        } finally {
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
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.delete(7)).isTrue();
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
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.findall().size()).isEqualTo(7);
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
    public void testFindById_Return_Category(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.findById(1))
                    .usingRecursiveComparison()
                    .isEqualTo(new Category.Builder()
                            .setId(1)
                            .setName("Аппаратная косметология")
                            .setDescription("Аппаратная косметология")
                            .build());
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
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.findById(8)).isNull();
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
    public void testFindByName_Return_Category(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.findByName("Аппаратная косметология"))
                    .usingRecursiveComparison()
                    .isEqualTo(new Category.Builder()
                            .setId(1)
                            .setName("Аппаратная косметология")
                            .setDescription("Аппаратная косметология")
                            .build());
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
    public void testFindByName_Return_Null(){
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.findByName("gjfdkf")).isNull();
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
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            List<Category> categories = categoryDao.findInterval(1,4);
            Assertions.assertThat(categories).size().isEqualTo(4);
            Assertions.assertThat(categories).doesNotContain(new Category.Builder()
                    .setId(1)
                    .setName("Аппаратная косметология")
                    .setDescription("Аппаратная косметология")
                    .build());
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
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            Assertions.assertThat(categoryDao.update(new Category.Builder()
                    .setId(1)
                    .setName("Аппаратная")
                    .setDescription("Аппаратная")
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