package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.ScoreDao;
import by.training.beauty.domain.Score;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement ScoreDao for database MySQL.
 */

public class ScoreDaoImpl implements ScoreDao {
    private static final Logger LOGGER = LogManager.getLogger(ScoreDaoImpl.class);
    private static final String SQL_FIND_BY_PROCEDURE =
            "SELECT score.id, score.user_id,score.value, score.appointment_id, " +
            "score.comment, score.date " +
            "FROM score LEFT JOIN (SELECT appointment.id " +
            "FROM appointment LEFT JOIN (SELECT procedure_employee.id " +
            "FROM procedure_employee " +
            "LEFT JOIN `procedure` ON procedure_employee.procedure_id = `procedure`.id " +
            "WHERE procedure.id = ?) as PE ON appointment.procedure_employee_id = PE.id " +
            "WHERE PE.id is not null) as AP " +
            "ON AP.id = score.appointment_id " +
            "WHERE AP.id is not null;";
    private Connection connection;
    private static final String SQL_CREATE = "INSERT INTO score(score.user_id, " +
            "score.value , score.appointment_id , score.comment, " +
            "score.date) VALUES(?,?,?,?,?);";
    private static final String SQL_FIND_INTERVAL = "SELECT score.id, score.user_id, " +
            "score.value, score.appointment_id, score.comment, " +
            "score.date FROM score WHERE score.id>0 LIMIT ?, ?;";
    private static final String SQL_FIND_ALL = "SELECT score.id, score.user_id, " +
            "score.value, score.appointment_id, score.comment, " +
            "score.date FROM score;";
    private static final String SQL_FIND_BY_ID = "SELECT score.id, score.user_id, " +
            "score.value, score.appointment_id, score.comment, " +
            "score.date FROM score WHERE score.id = ?;";
    private static final String SQL_DELETE = "DELETE FROM score WHERE score.id = ?;";
    private static final String SQL_UPDATE = "UPDATE score SET score.user_id = ?, " +
            "score.value = ?, score.appointment_id = ?, score.comment = ?," +
            "score.date = ? WHERE score.id = ?;";

    @Override
    public List<Score> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Score> scores = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Score score = new Score();
                score.setId(resultSet.getInt("score.id"));
                score.setUserId(resultSet.getInt("score.user_id"));
                score.setValue(resultSet.getByte("score.value"));
                score.setAppointmentId(resultSet.getInt("score.appointment_id"));
                score.setComment(resultSet.getString("score.comment"));
                score.setDate(resultSet.getTimestamp("score.date").toLocalDateTime());
                scores.add(score);
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
        return scores;
    }
    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(score.id) as count " +
                    "FROM score");
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
    public List<Score> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Score> scores = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1,begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                Score score = new Score();
                score.setId(resultSet.getInt("score.id"));
                score.setUserId(resultSet.getInt("score.user_id"));
                score.setValue(resultSet.getByte("score.value"));
                score.setAppointmentId(resultSet.getInt("score.appointment_id"));
                score.setComment(resultSet.getString("scpre.comment"));
                score.setDate(resultSet.getTimestamp("score.date").toLocalDateTime());
                scores.add(score);
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
        return scores;
    }

    @Override
    public Score findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Score score = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                score = new Score();
                score.setId(resultSet.getInt("score.id"));
                score.setUserId(resultSet.getInt("score.user_id"));
                score.setValue(resultSet.getByte("score.value"));
                score.setAppointmentId(resultSet.getInt("score.appointment_id"));
                score.setComment(resultSet.getString("score.comment"));
                score.setDate(resultSet.getTimestamp("score.date").toLocalDateTime());
            }
            return score;
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
    public Score create(Score score) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, score.getUserId());
            statement.setInt(2, score.getValue());
            statement.setInt(3, score.getAppointmentId());
            statement.setString(4, score.getComment());
            statement.setTimestamp(5, Timestamp.valueOf(score.getDate()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                score.setId(resultSet.getInt("GENERATED_KEY"));
                return score;
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
    public boolean update(Score score) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, score.getUserId());
            statement.setInt(2, score.getValue());
            statement.setInt(3, score.getAppointmentId());
            statement.setString(4, score.getComment());
            statement.setTimestamp(5, Timestamp.valueOf(score.getDate()));
            statement.setInt(6, score.getId());
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
    public List<Score> findByProcedure(int procedureId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Score> scores = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_PROCEDURE);
            statement.setInt(1, procedureId);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                Score score = new Score();
                score.setId(resultSet.getInt("score.id"));
                score.setUserId(resultSet.getInt("score.user_id"));
                score.setValue(resultSet.getByte("score.value"));
                score.setAppointmentId(resultSet.getInt("score.appointment_id"));
                score.setComment(resultSet.getString("score.comment"));
                score.setDate(resultSet.getTimestamp("score.date").toLocalDateTime());
                scores.add(score);
            }
            return scores;
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
