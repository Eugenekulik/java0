package by.training.beauty.service.spec;

import by.training.beauty.domain.User;
import by.training.beauty.dto.AppointmentDto;
import by.training.beauty.dto.ProcedureDto;
import by.training.beauty.dto.ScheduleDto;
import by.training.beauty.service.ServiceException;

import java.util.List;

public interface AdministrateService {
    int getPagecount(int tab) throws ServiceException;

    List<User> administrateUsers(int paginationPage) throws ServiceException;

    List<AppointmentDto> administrateAppointments(int paginationPage)
            throws ServiceException;

    List<ProcedureDto> administrateProcedures(int paginationPage)
            throws ServiceException;

    List<ScheduleDto> administrateSchedules(int paginationPage)
            throws ServiceException;
}
