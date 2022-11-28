package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.dao.spec.ProcedureDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.domain.Procedure;
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

public class ProcedureDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(ProcedureDaoImplTest.class);


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
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.count()).isEqualTo(6);
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
    public void testCreate_Return_Procedure() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.create(new Procedure.Builder()
                        .setName("newProcedure")
                        .setDescription("newProcedure")
                        .setElapsedTime(60)
                        .setCategoryId(1)
                        .build()))
                    .isNotNull();
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
    public void testDelete_Return_True(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.delete(3)).isTrue();
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
    public void testFindAll(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.findall().size()).isEqualTo(6);
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
    public void testFindById_Return_Procedure(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.findById(1))
                    .usingRecursiveComparison().ignoringFields("description")
                    .isEqualTo(new Procedure.Builder()
                            .setId(1)
                            .setName("Лазерное омоложение")
                            .setElapsedTime(60)
                            .setCategoryId(1)
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
    public void testFindById_Return_Null(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.findById(7)).isNull();
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
    public void testFindByName_Return_Procedure(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.findByName("Лазерное омоложение"))
                    .usingRecursiveComparison().ignoringFields("description")
                    .isEqualTo(new Procedure.Builder()
                            .setId(1)
                            .setName("Лазерное омоложение")
                            .setElapsedTime(60)
                            .setCategoryId(1)
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
    public void testFindByName_Return_Null(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.findByName("jddfjkfdf")).isNull();
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
    public void testFindInterval(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            List<Procedure> procedures = procedureDao.findInterval(2,3);
            Assertions.assertThat(procedures.size()).isEqualTo(3);
            Assertions.assertThat(procedures)
                    .usingRecursiveFieldByFieldElementComparatorIgnoringFields("description")
                    .doesNotContain(new Procedure.Builder()
                            .setId(1)
                            .setName("Лазерное омоложение")
                            .setElapsedTime(60)
                            .setCategoryId(1)
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
    public void testUpdate(){
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            Assertions.assertThat(procedureDao.update(new Procedure.Builder()
                            .setId(1)
                            .setName("Лазерное")
                            .setElapsedTime(45)
                            .setDescription("лазерное")
                            .setCategoryId(2)
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