package by.training.beauty.service.spec;

import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;

import java.util.List;

public interface UserService {
    User login(String login, String password) throws ServiceException;

    User registration(User user) throws ServiceException;

    boolean deleteUser(int id) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    List<User> employeesByProcedure(Procedure procedure)
            throws ServiceException;

    List<User> employeeList() throws ServiceException;

    User findById(int id);
}
