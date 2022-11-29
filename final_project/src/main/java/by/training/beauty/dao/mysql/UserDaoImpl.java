package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.UserDao;
import by.training.beauty.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement UserDao for database MySQL.
 */

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String SQL_FIND_EMPLOYEES_BY_PROCEDURE =
            "SELECT user.id, user.name, user.login, user.password, user.phone FROM user " +
            "RIGHT JOIN (SELECT procedure_employee.id, procedure_employee.employee_id " +
            "FROM procedure_employee LEFT JOIN `procedure` " +
            "ON procedure_employee.procedure_id = `procedure`.id " +
            "WHERE `procedure`.id = ?) as PE " +
            "ON PE.employee_id = user.id;";
    private static final String SQL_FIND_BY_APPOINTMENT =
            "SELECT user.id, user.login, user.password, user.name, user.phone " +
            "FROM user RIGHT JOIN (SELECT procedure_employee.employee_id FROM procedure_employee " +
            "LEFT JOIN appointment ON appointment.procedure_employee_id = procedure_employee.id " +
            "WHERE appointment.id = ?) as PE " +
            "ON user.id = PE.employee_id;";
    private Connection connection;
    private static final String SQL_CREATE =
            "INSERT INTO user(user.login, user.password, user.name, user.phone) " +
                    "VALUES(?,?,?,?);";
    private static final String SQL_FIND_ALL =
            "SELECT user.id, user.login, user.password, user.name, user.phone " +
                    "FROM user";
    private static final String SQL_FIND_INTERVAL =
            "SELECT user.id, user.login, user.password, user.name, user.phone " +
                    "FROM user WHERE user.id>0 LIMIT ?, ?";
    private static final String SQL_FIND_BY_ID =
            "SELECT user.id, user.login, user.password, user.name, user.phone " +
                    "FROM user WHERE user.id = ?;";
    private static final String SQL_DELETE =
            "DELETE FROM user WHERE user.id = ?;";
    private static final String SQL_UPDATE =
            "UPDATE user SET user.login = ?, user.password = ?, user.name = ?, user.phone = ? " +
                    "WHERE user.id = ?;";
    private static final String SQL_READ_BY_LOGIN =
            "SELECT user.id, user.login, user.password, user.name, user.phone " +
                    "FROM user WHERE user.login = ?;";
    private static final String SQL_READ_BY_NAME =
            "SELECT user.id, user.login, user.password, user.name, user.phone " +
                    "FROM user WHERE user.name = ?;";
    private static final String SQL_FIND_EMPLOYEES =
            "SELECT user.id, user.login, user.password, user.name, user.phone " +
                    "FROM user right join (user_role left join role on user_role.role_id = role.id) " +
                    "on user.id = user_role.user_id " +
                    "WHERE role.name = 'employee'";

    @Override
    public List<User> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setPhone(resultSet.getString("user.phone"));
                users.add(user);
            }
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return users;
    }

    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connection.prepareStatement("SELECT count(user.id) as count " +
                    "FROM user");
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return count;
    }

    @Override
    public List<User> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1, begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setPhone(resultSet.getString("user.phone"));
                users.add(user);
            }
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return users;
    }

    @Override
    public User findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setPhone(resultSet.getString("user.phone"));
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
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
            }
        }
    }

    @Override
    public User create(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPhone());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return user;
            }
            return null;
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
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
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
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getId());
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
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setPhone(resultSet.getString("user.phone"));
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public List<User> findEmployees() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_EMPLOYEES);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setPhone(resultSet.getString("user.phone"));
                users.add(user);
            }
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return users;
    }

    @Override
    public User findByName(String name) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_READ_BY_NAME);
            statement.setString(1, name);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                user = new User.Builder()
                        .setId(resultSet.getInt("user.id"))
                        .setLogin(resultSet.getString("user.login"))
                        .setPassword(resultSet.getString("user.password"))
                        .setName(resultSet.getString("user.name"))
                        .setPhone(resultSet.getString("user.phone"))
                        .build();
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public List<User> findEmployeesByProcedure(int procedureId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_EMPLOYEES_BY_PROCEDURE);
            statement.setInt(1,procedureId);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user.id"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setName(resultSet.getString("user.name"));
                user.setPhone(resultSet.getString("user.phone"));
                users.add(user);
            }
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return users;
    }

    @Override
    public User findEmployeeByAppointment(int appointmentId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_APPOINTMENT);
            statement.setInt(1, appointmentId);
            statement.execute();
            resultSet = statement.getResultSet();
            if(resultSet.next()) {
                user = new User.Builder()
                        .setId(resultSet.getInt("user.id"))
                        .setLogin(resultSet.getString("user.login"))
                        .setPassword(resultSet.getString("user.password"))
                        .setName(resultSet.getString("user.name"))
                        .setPhone(resultSet.getString("user.phone"))
                        .build();
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
