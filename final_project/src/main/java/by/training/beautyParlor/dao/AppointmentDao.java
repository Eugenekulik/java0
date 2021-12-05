package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Appointment;
import by.training.beautyParlor.domain.User;

import java.util.List;

public interface AppointmentDao extends Dao<Appointment> {
    List<Appointment> getUsersAppointment(User user) throws DaoException;
}
