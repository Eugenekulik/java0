package by.training.beauty.controller.action.implementation.employee;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.*;
import by.training.beauty.service.ScheduleService;
import by.training.beauty.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class implement interface Action
 * and allows getting schedule for employee.
 *
 * @see Action
 * @see ScheduleService
 */

public class ScheduleAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleAction.class);
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        return roles == null
                &&roles.contains(new Role("employee"))
                && request.getMethod().equals("GET");
    }

    @Override
    public String execute(HttpServletRequest request) {
        int pageCount  = 0;
        int paginationPage = 0;
        User employee = (User) request.getSession().getAttribute("user");
        ScheduleService scheduleService = new ScheduleService();
        try {
            List<Entity> entities = scheduleService.getSchedulesByEmployee(employee);
            List<Schedule> schedules = entities.stream()
                    .filter(Schedule.class::isInstance)
                    .map(Schedule.class::cast)
                    .sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
                            .collect(Collectors.toList());
            List<Appointment> appointments = entities.stream()
                    .filter(Appointment.class::isInstance)
                            .map(Appointment.class::cast)
                    .sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
                                    .collect(Collectors.toList());
            Set<LocalDate>  dates = new HashSet<>();
            dates.addAll(schedules.stream()
                    .map(schedule -> schedule.getDate().toLocalDate())
                    .collect(Collectors.toSet()));
            dates.addAll(appointments.stream()
                    .map(appointment -> appointment.getDate().toLocalDate())
                    .collect(Collectors.toSet()));
            try {
                paginationPage = Integer.parseInt(request.getParameter("paginationPage"));
            } catch (NumberFormatException e) {
                paginationPage = 1;
            }
            request.getSession().setAttribute("paginationPage",paginationPage);
            pageCount = (int) Math.ceil(dates.size()/10.0);
            request.getSession().setAttribute("dates", dates);
            request.getSession().setAttribute("pageCount",pageCount);
            return PageEnum.SCHEDULE.getPage();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get schedules");
        }
        return PageEnum.ERROR.getPage();
    }

}
