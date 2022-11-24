package by.training.beauty.dao.mysql;

import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.domain.User;
import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.AppointmentDao;
import by.training.beauty.domain.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement AppointmentDao for database MySQL.
 */

public class AppointmentDaoImpl implements AppointmentDao {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentDaoImpl.class);
    private static final String SQL_FIND_INTERVAL = "SELECT appointment.id, appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price FROM appointment WHERE appointment.id>0 LIMIT ?, ?;";
    private static final String SQL_CANCEL = "UPDATE appointment set status = 4 WHERE appointment.id = ?;";
    private static final String SQL_ARCHIVE = "UPDATE appointment set status 3 where appointment.date < now";
    private Connection connection;
    private static final String SQL_FIND_BY_USER = "SELECT appointment.id, appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price FROM appointment WHERE appointment.user_id = ?;";
    private static final String SQL_FIND_BY_EMPLOYEE = "SELECT appointment.id, appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price FROM appointment WHERE appointment.procedure_employee_id = ?;";
    private static final String SQL_CREATE = "INSERT INTO appointment(appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price) VALUES(?,?,?,?,?);";
    private static final String SQL_FIND_ALL = "SELECT appointment.id, appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price FROM appointment;";
    private static final String SQL_FIND_BY_ID = "SELECT appointment.id, appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price FROM appointment WHERE appointment.id = ?;";
    private static final String SQL_DELETE = "DELETE FROM appointment WHERE appointment.id = ?;";
    private static final String SQL_UPDATE = "UPDATE appointment SET appointment.status = ?," +
            "appointment.price = ? WHERE appointment.id = ?;";

    @Override
    public List<Appointment> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("appointment.id"));
                appointment.setUserId(resultSet.getInt("appointment.user_id"));
                appointment.setProcedureEmployeeId(resultSet.getInt("appointment.procedure_employee_id"));
                appointment.setDate(resultSet.getTimestamp("appointment.date").toLocalDateTime());
                appointment.setStatus(resultSet.getInt("appointment.status"));
                appointment.setPrice(resultSet.getDouble("appointment.price"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
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
        return appointments;
    }

    @Override
    public List<Appointment> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1, begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("appointment.id"));
                appointment.setUserId(resultSet.getInt("appointment.user_id"));
                appointment.setProcedureEmployeeId(resultSet.getInt("appointment.procedure_employee_id"));
                appointment.setDate(resultSet.getTimestamp("appointment.date").toLocalDateTime());
                appointment.setStatus(resultSet.getInt("appointment.status"));
                appointment.setPrice(resultSet.getDouble("appointment.price"));
                appointments.add(appointment);
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
        return appointments;
    }

    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(appointment.id) as count " +
                    "FROM appointment");
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
    public Appointment findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Appointment appointment = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                appointment = new Appointment();
                appointment.setId(resultSet.getInt("appointment.id"));
                appointment.setUserId(resultSet.getInt("appointment.user_id"));
                appointment.setProcedureEmployeeId(resultSet.getInt("appointment.procedure_employee_id"));
                appointment.setDate(resultSet.getTimestamp("appointment.date").toLocalDateTime());
                appointment.setStatus(resultSet.getInt("appointment.status"));
                appointment.setPrice(resultSet.getDouble("appointment.price"));
            }
            return appointment;
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
    public int create(Appointment appointment) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, appointment.getUserId());
            statement.setInt(2, appointment.getProcedureEmployeeId());
            statement.setTimestamp(3, Timestamp.valueOf(appointment.getDate()));
            statement.setInt(4, appointment.getStatus());
            statement.setDouble(5, appointment.getPrice());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while(resultSet.next()){
                return resultSet.getInt("GENERATED_KEY");
            }
            return 0;
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
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public boolean update(Appointment appointment) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, appointment.getStatus());
            statement.setDouble(2,appointment.getPrice());
            statement.setInt(3, appointment.getId());
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

    @Override
    public List<Appointment> getUsersAppointment(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_USER);
            statement.setInt(1,user.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("appointment.id"));
                appointment.setUserId(resultSet.getInt("appointment.user_id"));
                appointment.setProcedureEmployeeId(resultSet.getInt("appointment.procedure_employee_id"));
                appointment.setDate(resultSet.getTimestamp("appointment.date").toLocalDateTime());
                appointment.setStatus(resultSet.getInt("appointment.status"));
                appointment.setPrice(resultSet.getDouble("appointment.price"));
                appointments.add(appointment);
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
        return appointments;
    }

    @Override
    public List<Appointment> getEmployeeAppointment(ProcedureEmployee procedureEmployee) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_EMPLOYEE);
            statement.setInt(1,procedureEmployee.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("appointment.id"));
                appointment.setUserId(resultSet.getInt("appointment.user_id"));
                appointment.setProcedureEmployeeId(resultSet.getInt("appointment.procedure_employee_id"));
                appointment.setDate(resultSet.getTimestamp("appointment.date").toLocalDateTime());
                appointment.setStatus(resultSet.getInt("appointment.status"));
                appointment.setPrice(resultSet.getDouble("appointment.price"));
                appointments.add(appointment);
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
        return appointments;
    }

    @Override
    public boolean cancel(int id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CANCEL);
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
    public void archive() throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ARCHIVE);
            statement.executeUpdate();
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
}
