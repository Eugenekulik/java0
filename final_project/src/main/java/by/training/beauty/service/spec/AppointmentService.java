package by.training.beauty.service.spec;

import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;

import java.util.List;

public interface AppointmentService {
    void cancelAppointment(int id) throws ServiceException;

    List<Appointment> usersAppointment(User user) throws ServiceException;

    boolean addAppointment(Appointment appointment
            , int procedureId, int employeeId) throws ServiceException;

    void updateAppointment(Appointment appointment)
            throws ServiceException;

    void archive();
}
