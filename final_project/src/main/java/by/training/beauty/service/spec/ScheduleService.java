package by.training.beauty.service.spec;

import by.training.beauty.domain.Schedule;
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

    void deleteSchedule(Integer id) throws ServiceException;


    void archive();
}
