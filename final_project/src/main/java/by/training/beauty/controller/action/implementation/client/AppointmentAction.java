package by.training.beauty.controller.action.implementation.client;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.*;
import by.training.beauty.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implement interface Action
 * and execute command of getting list of appointment
 * of specific user
 *
 * @see Action
 * @see AppointmentService
 */

public class AppointmentAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentAction.class);
    private boolean redirect = false;
    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        String method = (request.getParameter("method")!=null)?"POST":"GET";
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        return roles != null
                && roles.contains(new Role("client"))
                && request.getMethod().equals(method);
    }


    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getParameter("method");
        String page = null;
        if(method == null) method = "get";
        switch (method){
            case "create":
                page = create(request);
                break;
            case "delete":
                delete(request);
                break;
            case "update":
                break;
            case "get":
                get(request);
                break;
            default:
                throw new UnsupportedOperationException(request.getRequestURI());
        }
        return page==null?PageEnum.APPOINTMENT.getPage():page;
    }


    private boolean delete(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        if (id != null) {
            try {
                ServiceFactory.getInstance()
                        .getAppointmentService()
                        .cancelAppointment(id);
                return true;
            } catch (ServiceException e) {
                LOGGER.warn(e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }


    public void get(HttpServletRequest request){
        try {
            AppointmentService appointmentService =
                    ServiceFactory.getInstance().getAppointmentService();
            User user = (User) request.getSession().getAttribute("user");
            List<Entity> entities = appointmentService.usersAppointment(user);
            List<Appointment> appointments = entities.stream()
                    .filter(Appointment.class::isInstance)
                    .map(Appointment.class::cast)
                    .collect(Collectors.toList());
            List<User> employees = entities.stream()
                    .filter(User.class::isInstance)
                    .map(User.class::cast)
                    .collect(Collectors.toList());
            List<Procedure> procedures = entities.stream()
                    .filter(Procedure.class::isInstance)
                    .map(Procedure.class::cast)
                    .collect(Collectors.toList());
            request.getSession().removeAttribute("tab");
            request.getSession().setAttribute("procedures",procedures);
            request.getSession().setAttribute("employees", employees);
            request.getSession().setAttribute("appointments", appointments);
            Integer tab = 1;
            tab = Integer.parseInt(request.getParameter("tab"));
            request.getSession().setAttribute("tab", tab);
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get appointments");
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
        }
    }


    private String create(HttpServletRequest request){
        if (request.getSession().getAttribute("procedure") == null) {
            request.setAttribute("error", "Sorry, server error! " +
                    "We will try to solve this problem");
            return PageEnum.ERROR.getPage();
        }
        if (request.getParameter("time") != null) {
            return createFinal(request);
        }
        return enterEmployeeAndDate(request);
    }

    private String createFinal(HttpServletRequest request){
        AppointmentService appointmentService =
                ServiceFactory.getInstance().getAppointmentService();
        try {
            User client = (User) request.getSession().getAttribute("user");
            Procedure procedure = (Procedure) request.getSession().getAttribute("procedure");
            int employeeId = (Integer) request.getSession().getAttribute("employee");
            LocalDate localDate = LocalDate.parse((String) request.getSession().getAttribute("date"));
            LocalTime localTime = LocalTime.parse(request.getParameter("time"));
            Appointment appointment = new Appointment();
            appointment.setUserId(client.getId());
            appointment.setDate(LocalDateTime.of(localDate, localTime));
            if (appointmentService.addAppointment(appointment, procedure.getId(), employeeId)) {
                request.getSession().removeAttribute("date");
                request.getSession().removeAttribute("employee");
                request.getSession().removeAttribute("procedure");
                return PageEnum.APPOINTMENT.getPage();
            } else {
                request.getSession().setAttribute("error", "an error occured while adding appointment");
                return PageEnum.ERROR.getPage();
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to add appointment");
            request.getSession().setAttribute("error", "an error occurred while adding appointment");
            return PageEnum.ERROR.getPage();
        }
    }

    private String enterEmployeeAndDate(HttpServletRequest request){
        UserService userService =
                ServiceFactory.getInstance().getUserService();
        Procedure procedure = (Procedure) request.getSession().getAttribute("procedure");
        try {
            List<User> employeeList = userService.employeesByProcedure(procedure);
            request.getSession().setAttribute("employeeList", employeeList);
            Integer employee = null;
            try{
                employee = Integer.parseInt(request.getParameter("employee"));
            } catch (NumberFormatException e){};
            if (employee == null) {
                request.setAttribute("error", "choose employee");
                return PageEnum.APPOINTMENT_CREATE_FORM.getPage();
            }
            request.getSession().setAttribute("employee", employee);
            String dateString = request.getParameter("date");
            if (dateString == null || dateString.equals("")) {
                return PageEnum.APPOINTMENT_CREATE_FORM.getPage();
            }
            request.getSession().setAttribute("date", dateString);
            ScheduleService scheduleService =
                    ServiceFactory.getInstance().getScheduleService();
            LocalDate date = LocalDate.parse((String) request.getSession()
                    .getAttribute("date"));
            List<LocalTime> schedules = scheduleService.schedulesByEmployeeDate(employee, date);
            request.getSession().setAttribute("schedules", schedules);
            return PageEnum.APPOINTMENT_CREATE_FORM.getPage();
        } catch (ServiceException e) {
            LOGGER.error("service exception", e);
        } catch (NumberFormatException e) {
            LOGGER.warn(e.getMessage());
            request.getSession().setAttribute("error", "wrong employee");
        }
        redirect = true;
        return "/error.html";
    }

}
