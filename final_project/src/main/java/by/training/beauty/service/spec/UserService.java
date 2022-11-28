package by.training.beauty.service.spec;

import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    User login(String login, String password) throws ServiceException;

    User registrate(User user) throws ServiceException;

    void deleteUser(Integer id) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    List<User> employeesByProcedure(Procedure procedure)
            throws ServiceException;

    List<User> employeeList() throws ServiceException;

    User findById(int id);
}
