package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.RoleDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.dao.spec.UserDao;
import by.training.beauty.dao.mysql.UserDaoImpl;
import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.pool.PooledConnection;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.spec.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateServiceImplTest.class);

    TransactionFactory transactionFactory;


    @BeforeClass
    public void init() {
        transactionFactory = mock(TransactionFactory.class);
        Transaction transaction = mock(Transaction.class);
        UserDao userDao = mock(UserDao.class);
        RoleDao roleDao = mock(RoleDao.class);
        try {
// Configure mock userDao
            when(userDao.findByLogin(anyString()))
                    .thenReturn(new User.Builder()
                            .setId(1)
                            .setLogin("client")
                            // password = clientuser
                            .setPassword("c56207bc713c9529e4e6e2ca7958d30cee4ddd9e5c4b53dd9d3132dd56816e08")
                            .setPhone("+375290000000")
                            .setName("client")
                            .build());
            when(userDao.findByLogin("newUser")).thenReturn(null);
            when(userDao.create(any())).thenReturn(new User.Builder()
                    .setId(1)
                    .setName("new user")
                    .setLogin("newUser")
                    .setPassword("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8")
                    .setPhone("+375337748378")
                    .build());
            when(userDao.findById(1))
                    .thenReturn(new User());
            when(userDao.update(any())).thenReturn(true);
            when(userDao.findById(2)).thenReturn(null);
            when(userDao.delete(1)).thenReturn(true);
            when(userDao.delete(2)).thenReturn(false);
            when(userDao.findEmployeesByProcedure(1))
                    .thenReturn(List.of(new User.Builder()
                            .setId(2).build()));
            when(userDao.findEmployees())
                    .thenReturn(List.of(new User.Builder().setId(2).build()));


// Configure mock roleDao
            when(roleDao.findByUser(1))
                    .thenReturn(List.of(new Role(1, "client")));
            when(roleDao.updateRolesForUser(any()))
                    .thenReturn(true);


// Configure mock transaction
            when(transaction.createDao(DaoEnum.USER.getDao())).thenReturn(userDao);
            when(transaction.createDao(DaoEnum.ROLE.getDao())).thenReturn(roleDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.login("client", "clientuser"))
                    .usingRecursiveComparison()
                    .isEqualTo(new User.Builder()
                            .setId(1)
                            .setLogin("client")
                            .setName("client")
                            .setPhone("+375290000000")
                            .setPassword("c56207bc713c9529e4e6e2ca7958d30cee4ddd9e5c4b53dd9d3132dd56816e08")
                            .addRole(new Role(1, "client"))
                            .build());
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to authorizate", e);
        }
    }


    @Test
    public void testRegistration() {

        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            User actual = userService.registration(new User.Builder()
                    .setName("new user")
                    .setLogin("newUser")
                    .setPassword("password")
                    .setPhone("+375337748378")
                    .build());
            User expected = new User.Builder()
                    .setId(1)
                    .setName("new user")
                    .setLogin("newUser")
                    .setPassword("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8")
                    .setPhone("+375337748378")
                    .addRole(new Role("client"))
                    .build();
            Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to registrate user", e);
        }
    }

    @Test
    public void testRegistration_false() {
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.registration(new User.Builder()
                    .setPassword("password")
                    .setLogin("client")
                    .build())).isNull();
        } catch (ServiceException e) {
            LOGGER.error("an error occurred while registering the user", e);
        }
    }

    @Test
    public void testDeleteUser_Return_True() {
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.deleteUser(1)).isTrue();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to delete user", e);
        }
    }

    @Test
    public void testDeleteUser_Return_False() {
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.deleteUser(2)).isFalse();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to delete user", e);
        }
    }

    @Test
    public void testUpdateUser_Return_True() {
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.updateUser(new User.Builder()
                    .setId(1)
                    .build())).isTrue();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to update user", e);
        }
    }

    @Test
    public void testUpdateUser_Return_False() {
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.updateUser(new User.Builder()
                    .setId(2)
                    .build())).isFalse();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to update user", e);
        }
    }


    @Test
    public void testFindById_Return_User() {
        UserService userService = new UserServiceImpl(transactionFactory);
        Assertions.assertThat(userService.findById(1)).isNotNull();

    }

    @Test
    public void testFindById_Return_Null() {
        UserService userService = new UserServiceImpl(transactionFactory);
        Assertions.assertThat(userService.findById(2)).isNull();
    }


    @Test
    public void testEmployeesByProcedure(){
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.employeesByProcedure(new Procedure.Builder()
                    .setId(1).build()))
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new User.Builder().setId(2).build())
                    .size().isEqualTo(1);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to update user", e);
        }
    }

    @Test
    public void testEmployeesList(){
        try {
            UserService userService = new UserServiceImpl(transactionFactory);
            Assertions.assertThat(userService.employeeList())
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new User.Builder().setId(2).build())
                    .size().isEqualTo(1);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to update user", e);
        }
    }
}