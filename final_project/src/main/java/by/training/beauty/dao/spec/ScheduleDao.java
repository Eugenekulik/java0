package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;
import by.training.beauty.domain.Schedule;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface extends Dao for Schedule and allows any special methods for schedule.
 *
 * @see Dao
 * @see Schedule
 * @see DaoException
 */

public interface ScheduleDao extends Dao<Schedule>{

    /**
     * This method allows getting oll schedules for special employee;
     * @param employeeId
     * @return List of Schedule
     * @throws DaoException
     */
    List<Schedule> findByEmployee(int employeeId) throws DaoException;

    /**
     * This method allows getting schedule by datetime for concrete employee.
     * @param time LocalDateTime
     * @param employeeId
     * @return Schedule
     * @throws DaoException
     */
    Schedule findByEmployeeDate(LocalDateTime time, int employeeId) throws DaoException;



    /**
     * This method should delete schedules that have not been used and whose time has passed.
     * @return
     */
    boolean archive() throws DaoException;
}
