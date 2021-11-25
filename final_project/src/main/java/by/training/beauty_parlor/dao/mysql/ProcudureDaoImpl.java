package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.dao.DaoException;
import by.training.beauty_parlor.dao.ProcedureDao;
import by.training.beauty_parlor.domain.Procedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcudureDaoImpl implements ProcedureDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private Connection connection;
    private static final String SQL_FIND_INTERVAL = "SELECT `procedure`.id, `procedure`.category_id, " +
            "`procedure`.name, `procedure`.description, `procedure`.elapsed_time " +
            "FROM `procedure` WHERE `procedure`.id>0 LIMIT ?, ?;";
    private static final String SQL_CREATE = "INSERT INTO `procedure`(`procedure`.category_id, " +
            "`procedure`.name, `procedure`.description, `procedure`.elapsed_time) VALUES(?,?,?,?);";
    private static final String SQL_FIND_ALL = "SELECT `procedure`.id, `procedure`.category_id, " +
            "`procedure`.name, `procedure`.description, `procedure`.elapsed_time FROM `procedure`;";
    private static final String SQL_FIND_BY_ID = "SELECT `procedure`.id, `procedure`.category_id, " +
            "`procedure`.name, `procedure`.description, `procedure`.elapsed_time " +
            "FROM `procedure` WHERE `procedure`.id = ?;";
    private static final String SQL_FIND_BY_NAME = "SELECT `procedure`.id, `procedure`.category_id, " +
            "`procedure`.name, `procedure`.description, `procedure`.elapsed_time " +
            "FROM `procedure` WHERE `procedure`.name = ?;";
    private static final String SQL_DELETE = "DELETE FROM `procedure` WHERE `procedure`.id = ?;";
    private static final String SQL_UPDATE = "UPDATE `procedure` SET `procedure`.category_id= ?, " +
            "`procedure`.name = ?, `procedure`.description = ?, `procedure`.elapsed_time = ? " +
            "WHERE `procedure`.id = ?;";
    @Override
    public List<Procedure> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Procedure> procedures = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Procedure procedure = new Procedure();
                procedure.setId(resultSet.getInt("procedure.id"));
                procedure.setCategoryId(resultSet.getInt("procedure.id"));
                procedure.setName(resultSet.getString("procedure.name"));
                procedure.setDescription(resultSet.getString("procedure.description"));
                procedure.setElapsedTime(resultSet.getInt("procedure.elapsed_time"));
                procedures.add(procedure);
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
        return procedures;
    }

    @Override
    public List<Procedure> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Procedure> procedures = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1,begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Procedure procedure = new Procedure();
                procedure.setId(resultSet.getInt("procedure.id"));
                procedure.setCategoryId(resultSet.getInt("procedure.id"));
                procedure.setName(resultSet.getString("procedure.name"));
                procedure.setDescription(resultSet.getString("procedure.description"));
                procedure.setElapsedTime(resultSet.getInt("procedure.elapsed_time"));
                procedures.add(procedure);
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
        return procedures;
    }

    @Override
    public Procedure findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Procedure procedure = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            if (resultSet.next()){
                procedure = new Procedure();
                procedure.setId(resultSet.getInt("procedure.id"));
                procedure.setCategoryId(resultSet.getInt("procedure.id"));
                procedure.setName(resultSet.getString("procedure.name"));
                procedure.setDescription(resultSet.getString("procedure.description"));
                procedure.setElapsedTime(resultSet.getInt("procedure.elapsed_time"));
            }
            return procedure;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean create(Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, procedure.getCategoryId());
            statement.setString(2, procedure.getName());
            statement.setString(3, procedure.getDescription());
            statement.setInt(4, procedure.getElapsedTime());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean update(Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, procedure.getCategoryId());
            statement.setString(2, procedure.getName());
            statement.setString(3, procedure.getDescription());
            statement.setInt(4, procedure.getElapsedTime());
            statement.setInt(5,procedure.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    @Override
    public Procedure findByName(String name) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Procedure procedure = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_NAME);
            statement.setString(1,name);
            statement.execute();
            resultSet = statement.getResultSet();
            if (resultSet.next()){
                procedure = new Procedure();
                procedure.setId(resultSet.getInt("procedure.id"));
                procedure.setCategoryId(resultSet.getInt("procedure.id"));
                procedure.setName(resultSet.getString("procedure.name"));
                procedure.setDescription(resultSet.getString("procedure.description"));
                procedure.setElapsedTime(resultSet.getInt("procedure.elapsed_time"));
            }
            return procedure;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
    }
}