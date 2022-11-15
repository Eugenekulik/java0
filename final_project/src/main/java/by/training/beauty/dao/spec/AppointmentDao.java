package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.domain.User;

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
    List<Appointment> getEmployeeAppointment(ProcedureEmployee procedureEmployee) throws DaoException;
}
