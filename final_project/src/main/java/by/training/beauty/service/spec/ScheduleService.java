package by.training.beauty.service.spec;

import by.training.beauty.service.ServiceException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {
    List<LocalTime> schedulesByEmployeeDate(int selectedEmployee, LocalDate date)
            throws ServiceException;

    boolean addSchedule(int employeeId, LocalDate date)
            throws ServiceException;

    boolean deleteSchedule(int id) throws ServiceException;


    boolean archive();
}
