package by.training.beauty.dao;

import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Score;

import java.util.List;

/**
 * This interface extends Dao for Score.
 *
 * @see Dao
 * @see Score
 */

public interface ScoreDao extends Dao<Score> {
    public List<Score> findByAppointment(Appointment appointment) throws DaoException;
}
