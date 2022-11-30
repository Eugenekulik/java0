package by.training.beauty.service.spec;

import by.training.beauty.domain.Appointment;
import by.training.beauty.service.ServiceException;

import java.util.List;

public interface AppointmentService {
    boolean cancelAppointment(int id) throws ServiceException;

    List<Appointment> usersAppointment(int userId) throws ServiceException;

    boolean addAppointment(Appointment appointment
            , int procedureId, int employeeId) throws ServiceException;

    boolean updateAppointment(Appointment appointment)
            throws ServiceException;

    void archive();
}
