package by.training.beauty.dao;

import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Schedule;
import by.training.beauty.domain.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface extends Dao for Schedule.
 *
 * @see Dao
 * @see Schedule
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
     * This method allows getting schedule by datetime.
     * @param time LocalDateTime
     * @return Schedule
     * @throws DaoException
     */
    Schedule findByEmployeeDate(LocalDateTime time, User employee) throws DaoException;

    Schedule findByAppointment(Appointment appointment) throws  DaoException;
}
