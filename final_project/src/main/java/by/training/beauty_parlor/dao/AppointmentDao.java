package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.Appointment;
import by.training.beauty_parlor.domain.User;

import java.util.List;

public interface AppointmentDao extends Dao<Appointment> {
    List<Appointment> getUsersAppointment(User user) throws DaoException;
}
