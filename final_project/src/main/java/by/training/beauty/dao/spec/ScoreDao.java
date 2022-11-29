package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;
import by.training.beauty.domain.Score;

import java.util.List;

/**
 * This interface extends Dao for Score.
 *
 * @see Dao
 * @see Score
 */

public interface ScoreDao extends Dao<Score> {
    public List<Score> findByProcedure(int appointmentId) throws DaoException;
}
