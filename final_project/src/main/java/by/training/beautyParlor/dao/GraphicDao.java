package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Graphic;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface extends Dao for Graphic.
 *
 * @see Dao
 * @see Graphic
 */

public interface GraphicDao extends Dao<Graphic>{

    /**
     * This method allows getting oll Graphics for special employee;
     * @param employeeId
     * @return List of Graphics
     * @throws DaoException
     */
    List<Graphic> findByEmployee(int employeeId) throws DaoException;
    Graphic findByDate(LocalDateTime time) throws DaoException;
}
