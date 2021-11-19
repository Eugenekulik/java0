package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.dao.UserDao;
import by.training.beauty_parlor.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private Connection connection;
    private static final String SQL_CREATE = "INSERT INTO user(user.login, user.password, " +
            "user.name, user.role) VALUES(?,?,?,?);";
    private static final String SQL_FIND_ALL = "SELECT user.id, user.login, user.password, " +
            "user.name, user.role FROM user";
    private static final String SQL_FIND_BY_ID = "SELECT user.id, user.login, user.password, " +
            "user.name, user.role FROM user WHERE user.id = ?;";
    private static final String SQL_DELETE = "DELETE FROM user WHERE user.id = ?;";
    private static final String SQL_UPDATE = "UPDATE user SET user.login = ?, user.password = ?, " +
            "user.name = ?, user.role = ? WHERE user.id = ?;";
    private static final String SQL_READ_BY_LOGIN_PASSWORD = "SELECT user.id, user.login, user.password, " +
            "user.name, user.role FROM user WHERE user.login = ? AND user.password = ?;";
    private static final String SQL_READ_BY_LOGIN = "SELECT user.id, user.login, user.password, " +
            "user.name, user.role FROM user WHERE user.login = ?;";
    @Override
    public List<User> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setRole(resultSet.getString("user.role"));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
        return users;
    }

    @Override
    public User findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setRole(resultSet.getString("user.role"));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getRole());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getRole());
            statement.setInt(5,user.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public User findByLogin(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_READ_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();
            resultSet = statement.getResultSet();
            User user = null;
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setRole(resultSet.getString("user.role"));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_READ_BY_LOGIN);
            statement.setString(1, login);
            statement.execute();
            resultSet = statement.getResultSet();
            User user = null;
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setRole(resultSet.getString("user.role"));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
