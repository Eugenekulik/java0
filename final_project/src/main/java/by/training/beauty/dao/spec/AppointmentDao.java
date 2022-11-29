package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;
import by.training.beauty.domain.Appointment;

import java.util.List;

/**
 * This interface extends Dao and
 * add functional work with Appointment.
 *
 * @see Dao
 * @see Appointment
 */

public interface AppointmentDao extends Dao<Appointment> {
    /**
     * This method allows getting appointment list for special user.
     *
     * @param userId@return List of appointment special user.
     * @throws DaoException
     */
    List<Appointment> getUserAppointments(int userId) throws DaoException;
    List<Appointment> getEmployeeAppointments(int procedureEmployeeId) throws DaoException;

    boolean cancel(int id) throws DaoException;

    /**
     * This method archiving complete appointments.
     */
    void archive() throws DaoException;

}
