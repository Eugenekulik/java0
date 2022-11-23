package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.RoleDao;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    //Connection with database
    private Connection connection;

    //CONSTANTS SQL QUERIES
    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);
    private static final String SQL_FIND_BY_USER =
            "SELECT role.id, role.name " +
            "from role left join user_role on role.id = user_role.role_id" +
            " where user_role.user_id = ?";
    private static final String SQL_DELETE_FOR_USER =
            "DELETE FROM user_role where user_role.user_id = ?";
    private static final String SQL_FIND_ALL =
            "SELECT role.id, role.name from role ";
    private static final String SQL_FIND_INTERVAL =
            "SELECT role.id, role.name from role WHERE role.id>0 LIMIT ?, ?";
    private static final String SQL_FIND_BY_ID =
            "SELECT role.id, role.name from role where role.id = ?;";
    private static final String SQL_DELETE =
            "DELETE FROM role WHERE role.id = ?;";
    private static final String SQL_CREATE =
            "INSERT INTO role(role.name) VALUES(?);";
    private static final String SQL_UPDATE =
            "UPDATE role SET role.name = ? WHERE role.id = ?;";
    private static final String SQL_UPDATE_ROLES_FOR_USER =
            "insert ignore into user_role(user_id, role_id)" +
            "VALUES (?,?)";


    @Override
    public List<Role> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Role role = new Role();
                role.setId(resultSet.getInt("role.id"));
                role.setName(resultSet.getString("role.name"));
                roles.add(role);
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return roles;
    }

    @Override
    public List<Role> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1,begin);
            statement.setInt(2,count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Role role = new Role();
                role.setName(resultSet.getString("role.name"));
                role.setId(resultSet.getInt("role.id"));
                roles.add(role);
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return roles;
    }

    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(role.id) as count " +
                    "FROM role");
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                count = resultSet.getInt("count");
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

    /**
     * This method return Role from storage by id or null if not exist.
     * @param id identifier of role
     * @return Role or null
     * @throws DaoException
     */
    @Override
    public Role findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Role role = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                role = new Role();
                role.setId(resultSet.getInt("role.id"));
                role.setName(resultSet.getString("role.name"));
            }
            return role;
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
    public List<Role> findByUser(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> roles = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_USER);
            statement.setInt(1,user.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Role role = new Role();
                role.setId(resultSet.getInt("role.id"));
                role.setName(resultSet.getString("role.name"));
                roles.add(role);
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
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return roles;
    }

    @Override
    public boolean updateRolesForUser(User user) throws DaoException {
        PreparedStatement insertStatement = null;
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = connection.prepareStatement(SQL_DELETE_FOR_USER);
            deleteStatement.setInt(1,user.getId());
            deleteStatement.executeUpdate();
            insertStatement = connection.prepareStatement(SQL_UPDATE_ROLES_FOR_USER, Statement.RETURN_GENERATED_KEYS);
            List<Role> roles = user.getRoles();
            for (Role role:roles) {
                insertStatement.setInt(1, user.getId());
                insertStatement.setInt(2, role.getId());
                insertStatement.addBatch();
            }
            insertStatement.executeBatch();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(deleteStatement != null) deleteStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if(insertStatement != null) insertStatement.close();
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
    public int create(Role role) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,role.getName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            int id = 0;
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }
            return id;
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
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public boolean update(Role role) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, role.getName());
            statement.setInt(2,role.getId());
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
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
