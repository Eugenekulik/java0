package by.training.beautyParlor.dao.mysql;

import by.training.beautyParlor.dao.GraphicDao;
import by.training.beautyParlor.domain.Graphic;
import by.training.beautyParlor.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GraphicDaoImpl implements GraphicDao {
    private static final Logger LOGGER = LogManager.getLogger(GraphicDao.class);
    private static final String SQL_FIND_INTERVAL = "SELECT graphic.id, graphic.employee_id, graphic.date " +
            "from graphic WHERE graphic.id>0 LIMIT ?, ?;";
    private Connection connection;
    private static final String SQL_FIND_BY_EMPLOYEE = "SELECT graphic.id, graphic.employee_id, graphic.date " +
            "from graphic WHERE graphic.employee_id = ?;";
    private static final String SQL_FIND_BY_DATE = "SELECT graphic.id, graphic.employee_id, " +
            "graphic.date FROM graphic WHERE graphic.date = ?;";
    private static final String SQL_CREATE = "INSERT INTO graphic(graphic.employee_id, graphic.date) VALUES(?,?);";
    private static final String SQL_FIND_ALL = "SELECT graphic.id, graphic.employee_id, graphic.date from graphic;";
    private static final String SQL_FIND_BY_ID = "SELECT graphic.id, graphic.employee_id, graphic.date WHERE graphic.id = ?;";
    private static final String SQL_DELETE = "DELETE FROM graphic WHERE graphic.id = ?;";
    private static final String SQL_UPDATE = "UPDATE graphic SET graphic.employee_id = ?, graphic.date = ? WHERE graphic.id = ?;";
    @Override
    public List<Graphic> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Graphic> graphics = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Graphic graphic = new Graphic();
                graphic.setId(resultSet.getInt("graphic.id"));
                graphic.setEmployeeId(resultSet.getInt("graphic.employee_id"));
                graphic.setDate(resultSet.getTimestamp("graphic.date").toLocalDateTime());
                graphics.add(graphic);
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
        return graphics;
    }

    @Override
    public List<Graphic> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Graphic> graphics = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1,begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Graphic graphic = new Graphic();
                graphic.setId(resultSet.getInt("graphic.id"));
                graphic.setEmployeeId(resultSet.getInt("graphic.employee_id"));
                graphic.setDate(resultSet.getTimestamp("graphic.date").toLocalDateTime());
                graphics.add(graphic);
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
        return graphics;
    }

    @Override
    public Graphic findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Graphic graphic = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                graphic = new Graphic();
                graphic.setId(resultSet.getInt("graphic.id"));
                graphic.setEmployeeId(resultSet.getInt("graphic.employee_id"));
                graphic.setDate(resultSet.getTimestamp("graphic.date").toLocalDateTime());
            }
            return graphic;
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
    public boolean create(Graphic graphic) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, graphic.getEmployeeId());
            statement.setTimestamp(2, Timestamp.valueOf(graphic.getDate()));
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
    public boolean update(Graphic graphic) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, graphic.getEmployeeId());
            statement.setTimestamp(2, Timestamp.valueOf(graphic.getDate()));
            statement.setInt(3,graphic.getId());
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
    public List<Graphic> findByEmployee(int employeeId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Graphic> graphics = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_EMPLOYEE);
            statement.setInt(1, employeeId);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Graphic graphic = new Graphic();
                graphic.setId(resultSet.getInt("graphic.id"));
                graphic.setEmployeeId(resultSet.getInt("graphic.employee_id"));
                graphic.setDate(resultSet.getTimestamp("graphic.date").toLocalDateTime());
                graphics.add(graphic);
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
        return graphics;
    }

    @Override
    public Graphic findByDate(LocalDateTime date) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Graphic graphic = new Graphic();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_DATE);
            statement.setTimestamp(1, Timestamp.valueOf(date));
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                graphic.setId(resultSet.getInt("graphic.id"));
                graphic.setEmployeeId(resultSet.getInt("graphic.employee_id"));
                graphic.setDate(resultSet.getTimestamp("graphic.date").toLocalDateTime());
            }
            return graphic;
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
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(graphic.id) as count " +
                    "FROM graphic");
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                count = resultSet.getInt("count");
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
        return count;
    }
}
