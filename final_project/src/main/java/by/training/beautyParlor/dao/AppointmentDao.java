package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Appointment;
import by.training.beautyParlor.domain.User;

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
     * @param user User, which appointment we want to get.
     * @return List of appointment special user.
     * @throws DaoException
     */
    List<Appointment> getUsersAppointment(User user) throws DaoException;
}
