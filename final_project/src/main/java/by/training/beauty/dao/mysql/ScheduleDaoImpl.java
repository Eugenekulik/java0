package by.training.beauty.dao.mysql;

import by.training.beauty.dao.spec.ScheduleDao;
import by.training.beauty.domain.Schedule;
import by.training.beauty.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement ScheduleDao for database MySQL.
 */

public class ScheduleDaoImpl implements ScheduleDao {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleDaoImpl.class);
    private static final String SQL_FIND_INTERVAL =
            "SELECT schedule.id, schedule.employee_id, schedule.date, schedule.appointment_id " +
            "FROM schedule " +
            "WHERE schedule.id>0 LIMIT ?, ?;";
    private static final String SQL_ARCHIVE =
            "DELETE FROM schedule " +
            "where schedule.date < now() and schedule.appointment_id is null";
    private Connection connection;
    private static final String SQL_FIND_BY_EMPLOYEE =
            "SELECT schedule.id, schedule.employee_id, schedule.date, schedule.appointment_id " +
            "FROM schedule " +
            "WHERE schedule.employee_id = ? and schedule.appointment_id is null";
    private static final String SQL_FIND_BY_EMPLOYEE_DATE =
            "SELECT schedule.id, schedule.employee_id, schedule.date, schedule.appointment_id " +
            "FROM schedule " +
            "WHERE schedule.date = ? and schedule.employee_id = ?;";
    private static final String SQL_CREATE =
            "INSERT INTO schedule(schedule.employee_id, schedule.date) " +
            "VALUES(?,?);";
    private static final String SQL_FIND_ALL =
            "SELECT schedule.id, schedule.employee_id, schedule.date, schedule.appointment_id " +
            "FROM schedule;";
    private static final String SQL_FIND_BY_ID =
            "SELECT schedule.id, schedule.employee_id, schedule.date, schedule.appointment_id " +
            "FROM schedule " +
            "WHERE schedule.id = ?;";
    private static final String SQL_DELETE =
            "DELETE FROM schedule WHERE schedule.id = ?;";
    private static final String SQL_UPDATE =
            "UPDATE schedule " +
            "SET schedule.employee_id = ?, schedule.date = ?, schedule.appointment_id = ? " +
            "WHERE schedule.id = ?;";
    @Override
    public List<Schedule> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Schedule> schedules = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getInt("schedule.id"));
                schedule.setEmployeeId(resultSet.getInt("schedule.employee_id"));
                schedule.setDate(resultSet.getTimestamp("schedule.date").toLocalDateTime());
                schedule.setAppointmentId(resultSet.getInt("schedule.appointment_id"));
                schedules.add(schedule);
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
        return schedules;
    }

    @Override
    public List<Schedule> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Schedule> schedules = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1,begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getInt("schedule.id"));
                schedule.setEmployeeId(resultSet.getInt("schedule.employee_id"));
                schedule.setDate(resultSet.getTimestamp("schedule.date").toLocalDateTime());
                schedule.setAppointmentId(resultSet.getInt("schedule.appointment_id"));
                schedules.add(schedule);
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
        return schedules;
    }

    @Override
    public Schedule findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Schedule schedule = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                schedule = new Schedule();
                schedule.setId(resultSet.getInt("schedule.id"));
                schedule.setEmployeeId(resultSet.getInt("schedule.employee_id"));
                schedule.setDate(resultSet.getTimestamp("schedule.date").toLocalDateTime());
                schedule.setAppointmentId(resultSet.getInt("schedule.appointment_id"));
            }
            return schedule;
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
    public Schedule create(Schedule schedule) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, schedule.getEmployeeId());
            statement.setTimestamp(2, Timestamp.valueOf(schedule.getDate()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while(resultSet.next()){
                schedule.setId(resultSet.getInt("GENERATED_KEY"));
                return schedule;
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
    public boolean update(Schedule schedule) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, schedule.getEmployeeId());
            statement.setTimestamp(2, Timestamp.valueOf(schedule.getDate()));
            if(schedule.getAppointmentId() != 0)
                statement.setInt(3, schedule.getAppointmentId());
            else statement.setNull(3,Types.INTEGER);
            statement.setInt(4, schedule.getId());
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
    public List<Schedule> findByEmployee(int employeeId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Schedule> schedules = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_EMPLOYEE);
            statement.setInt(1, employeeId);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getInt("schedule.id"));
                schedule.setEmployeeId(resultSet.getInt("schedule.employee_id"));
                schedule.setDate(resultSet.getTimestamp("schedule.date").toLocalDateTime());
                schedule.setAppointmentId(resultSet.getInt("schedule.appointment_id"));
                schedules.add(schedule);
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
        return schedules;
    }

    @Override
    public Schedule findByEmployeeDate(LocalDateTime date, int employeeId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Schedule schedule = new Schedule();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_EMPLOYEE_DATE);
            statement.setTimestamp(1, Timestamp.valueOf(date));
            statement.setInt(2, employeeId);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                schedule.setId(resultSet.getInt("schedule.id"));
                schedule.setEmployeeId(resultSet.getInt("schedule.employee_id"));
                schedule.setDate(resultSet.getTimestamp("schedule.date").toLocalDateTime());
                schedule.setAppointmentId(resultSet.getInt("schedule.appointment_id"));
            }
            return schedule;
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

    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(schedule.id) as count " +
                    "FROM schedule");
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
}
