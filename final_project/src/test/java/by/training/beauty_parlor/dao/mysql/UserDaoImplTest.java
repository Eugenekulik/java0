package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.domain.User;
import org.testng.annotations.Test;

import java.sql.*;

import static org.testng.Assert.*;

public class UserDaoImplTest {

    @Test
    public void testFindall() {

    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testCreate() {

    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testFindByLogin() {
        User user = new User();
        user.setId(1);
        user.setLogin("admin");
        user.setPassword("admin");
        user.setName("admin");
        user.setRole("admin");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/beauty_parlor",
                    "bp_app", "bppassword");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user.login = 'admin';");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                User user1 = new User();
                user1.setId(resultSet.getInt("user.id"));
                user1.setLogin(resultSet.getString("user.login"));
                user1.setPassword(resultSet.getString("user.password"));
                user1.setRole(resultSet.getString("user.role"));
                assertEquals(user,user1);
            }
        } catch (SQLException e) {}


    }

    @Test
    public void testSetConnection() {
    }
}