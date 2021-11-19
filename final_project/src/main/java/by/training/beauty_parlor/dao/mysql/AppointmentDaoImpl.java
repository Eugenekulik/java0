package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.dao.AppointmentDao;
import by.training.beauty_parlor.domain.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private Connection connection;
    private static final String SQL_CREATE = "INSERT INTO appointment(appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price) VALUES(?,?,?,?,?);";
    private static final String SQL_FIND_ALL = "SELECT appointment.id, appointment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price FROM appointment;";
    private static final String SQL_FIND_BY_ID = "SELECT appointment.id, appintment.user_id, " +
            "appointment.procedure_employee_id, appointment.date, appointment.status, " +
            "appointment.price FROM appointment WHERE appointment.id = ?;";
    private static final String SQL_DELETE = "DELETE FROM appointment WHERE appointment.id = ?;";
    private static final String SQL_UPDATE = "UPDATE  SET appointment.user_id = ?, " +
            "appointment.procedure_employee_id = ?, appointment.date = ?, appointment.status = ?," +
            "appointment.price WHERE appointment.id = ?;";

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
                appointment.setProcedureEmployeeId(resultSet.getInt("appointment.password"));
                appointment.setDate(resultSet.getDate("appointment.date"));
                appointment.setStatus(resultSet.getBoolean("appointment.status"));
                appointment.setPrice(resultSet.getInt("appointment.price"));
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
        return appointments;
    }

    @Override
    public Appointment findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Appointment appointment = new Appointment();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                appointment.setId(resultSet.getInt("appointment.id"));
                appointment.setUserId(resultSet.getInt("appointment.user_id"));
                appointment.setProcedureEmployeeId(resultSet.getInt("appointment.password"));
                appointment.setDate(resultSet.getDate("appointment.date"));
                appointment.setStatus(resultSet.getBoolean("appointment.status"));
                appointment.setPrice(resultSet.getInt("appointment.price"));
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
    public boolean create(Appointment appointment) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, appointment.getUserId());
            statement.setInt(2, appointment.getProcedureEmployeeId());
            statement.setDate(3, appointment.getDate());
            statement.setBoolean(4, appointment.isStatus());
            statement.setInt(5, appointment.getPrice());
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
    public boolean update(Appointment appointment) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, appointment.getUserId());
            statement.setInt(2, appointment.getProcedureEmployeeId());
            statement.setDate(3, appointment.getDate());
            statement.setBoolean(4, appointment.isStatus());
            statement.setInt(5,appointment.getPrice());
            statement.setInt(6, appointment.getId());
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
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
