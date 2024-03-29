package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.ProcedureDao;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Procedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement ProcedureDao for database MySQL.
 */

public class ProcedureDaoImpl implements ProcedureDao {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureDaoImpl.class);
    private static final String SQL_FIND_BY_PROCEDURE_EMPLOYEE =
            "SELECT `procedure`.id, `procedure`.category_id, `procedure`.name, " +
            "`procedure`.description, `procedure`.elapsed_time " +
            "FROM `procedure` LEFT JOIN procedure_emplyee " +
            "ON `procedure`.id = procedure_employee.procedure_id " +
            "WHERE procedure_employee.id = ?";
    private static final String SQL_FIND_BY_APPOINTMENT =
            "SELECT `procedure`.id, `procedure`.category_id, `procedure`.name, " +
                    "`procedure`.description, `procedure`.elapsed_time " +
                    "FROM `procedure` RIGHT JOIN " +
                    "(SELECT procedure_employee.procedure_id FROM procedure_employee LEFT JOIN appointment " +
                    "ON appointment.procedure_employee_id = procedure_employee.id " +
                    "WHERE appointment.id = ?) as PE " +
                    "ON `procedure`.id = PE.procedure_id;";
    private Connection connection;
    private static final String SQL_FIND_INTERVAL =
            "SELECT `procedure`.id, `procedure`.category_id, `procedure`.name, " +
            "`procedure`.description, `procedure`.elapsed_time " +
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
                procedure.setCategoryId(resultSet.getInt("procedure.category_id"));
                procedure.setName(resultSet.getString("procedure.name"));
                procedure.setDescription(resultSet.getString("procedure.description"));
                procedure.setElapsedTime(resultSet.getInt("procedure.elapsed_time"));
                procedures.add(procedure);
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
        return procedures;
    }
    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(`procedure`.id) as count " +
                    "FROM `procedure`");
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
                procedure.setCategoryId(resultSet.getInt("procedure.category_id"));
                procedure.setName(resultSet.getString("procedure.name"));
                procedure.setDescription(resultSet.getString("procedure.description"));
                procedure.setElapsedTime(resultSet.getInt("procedure.elapsed_time"));
                procedures.add(procedure);
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
    public Procedure create(Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, procedure.getCategoryId());
            statement.setString(2, procedure.getName());
            statement.setString(3, procedure.getDescription());
            statement.setInt(4, procedure.getElapsedTime());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while(resultSet.next()){
                procedure.setId(resultSet.getInt("GENERATED_KEY"));
                return procedure;
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
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
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
    public Procedure findByAppointment(int appointmentId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Procedure procedure = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_APPOINTMENT);
            statement.setInt(1, appointmentId);
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
}
