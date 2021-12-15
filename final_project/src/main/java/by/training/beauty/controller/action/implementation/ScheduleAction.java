package by.training.beauty.controller.action.implementation;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Entity;
import by.training.beauty.domain.Schedule;
import by.training.beauty.domain.User;
import by.training.beauty.service.ScheduleService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
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
    public Set<String> getRoles() {
        return Set.of("employee");
    }

    @Override
    public String execute(HttpServletRequest request) {
        /*int pageCount  = 0;
        int paginationPage = 0;*/
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
            request.getSession().setAttribute("schedules",schedules);
            request.getSession().setAttribute("appointments",appointments);
            return PageEnum.SCHEDULE.getPage();
        } catch (ServiceException e){
            LOGGER.error("it is impossible to get schedules");
        }
        return PageEnum.ERROR.getPage();
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
