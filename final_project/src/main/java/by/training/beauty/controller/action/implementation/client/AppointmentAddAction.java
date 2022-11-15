package by.training.beauty.controller.action.implementation.client;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.User;
import by.training.beauty.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

/**
 * This class implement interface action and give opportunity
 * to add appointment for client.
 * <br/>
 * At client by HttpRequest comes tree parameters(date, employee and time)
 * with which AppointmentService create appointment.
 *
 * @see Action
 * @see Appointment
 * @see AppointmentService
 */
public class AppointmentAddAction implements Action {

    //CONSTANTS
    private static final String PROCEDURE = "procedure";
    private static final String EMPLOYEE = "selectedEmployee";
    private static final String DATE = "selectedDate";
    private static final String TIME = "timeSelect";
    private static final String ERROR = "error";

    //LOGGER
    private static final Logger LOGGER = LogManager
            .getLogger(AppointmentAddAction.class);

    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("client");
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(TIME) != null) {
            AppointmentService appointmentService =
                    ServiceFactory.getInstance().getAppointmentService();
            try {
                User client = (User) request.getSession().getAttribute("user");
                Procedure procedure = (Procedure) request.getSession().getAttribute(PROCEDURE);
                Integer employee = (Integer) request.getSession().getAttribute(EMPLOYEE);
                LocalDate localDate = LocalDate.parse((String) request.getSession().getAttribute(DATE));
                LocalTime localTime = LocalTime.parse(request.getParameter(TIME));
                Appointment appointment = new Appointment();
                appointment.setUserId(client.getId());
                appointment.setDate(LocalDateTime.of(localDate, localTime));
                if (appointmentService.addAppointment(appointment, procedure, employee)) {
                    request.getSession().removeAttribute(DATE);
                    request.getSession().removeAttribute(EMPLOYEE);
                    request.getSession().removeAttribute(PROCEDURE);
                    return PageEnum.APPOINTMENT.getPage();
                } else {
                    request.getSession().setAttribute(ERROR, "an error occured while adding appointment");
                    return PageEnum.ERROR.getPage();
                }
            } catch (ServiceException e) {
                LOGGER.error("it is impossible to add appointment");
                request.getSession().setAttribute(ERROR, "an error occurred while adding appointment");
                return PageEnum.ERROR.getPage();
            }
        }
        Procedure procedure = (Procedure) request.getSession().getAttribute(PROCEDURE);
        if (procedure == null) {
            request.setAttribute(ERROR, "Sorry, server error! " +
                    "We will try to solve this problem");
            return PageEnum.ERROR.getPage();
        }
        EmployeeService employeeService =
                ServiceFactory.getInstance().getEmployeeService();
        try {
            List<User> employeeList = employeeService.employeesByProcedure(procedure);
            request.getSession().setAttribute("employeeList", employeeList);
            Integer selectedEmployee = Integer.parseInt(request.getParameter("employeeSelect"));
            if (selectedEmployee == null) {
                return PageEnum.APPOINTMENT_ADD.getPage();
            }
            request.getSession().setAttribute(EMPLOYEE, selectedEmployee);
            String dateString = request.getParameter("dateSelect");
            if (dateString.equals("")) {
                return PageEnum.APPOINTMENT_ADD.getPage();
            }
            request.getSession().setAttribute(DATE, dateString);
            ScheduleService scheduleService =
                    ServiceFactory.getInstance().getScheduleService();
            LocalDate date = LocalDate.parse((String) request.getSession()
                    .getAttribute(DATE));
            List<LocalTime> schedules = scheduleService.schedulesByEmployeeDate(selectedEmployee, date);
            request.getSession().setAttribute("schedules", schedules);
        } catch (ServiceException e) {
            LOGGER.error("service exception", e);
        } catch (NumberFormatException e) {
            LOGGER.warn(e.getMessage());
        }
        return PageEnum.APPOINTMENT_ADD.getPage();
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
