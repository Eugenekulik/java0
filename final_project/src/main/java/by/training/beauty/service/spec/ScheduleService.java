package by.training.beauty.service.spec;

import by.training.beauty.domain.Entity;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {
    List<LocalTime> schedulesByEmployeeDate(int selectedEmployee, LocalDate date)
            throws ServiceException;

    void addSchedule(int employeeId, LocalDate date)
            throws ServiceException;

    void deleteschedule(Integer id) throws ServiceException;

    List<Entity> getSchedulesByEmployee(User employee)
            throws ServiceException;

    void archive();
}
