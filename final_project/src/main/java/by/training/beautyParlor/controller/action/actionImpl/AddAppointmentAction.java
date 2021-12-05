package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.domain.Appointment;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.service.ServiceException;
import by.training.beautyParlor.service.AppointmentService;
import by.training.beautyParlor.service.EmployeeService;
import by.training.beautyParlor.service.GraphicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

public class AddAppointmentAction implements Action {
    private static final Logger LOGGER = LogManager
            .getLogger(AddAppointmentAction.class);

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
        if (request.getParameter("timeSelect") != null) {
            AppointmentService appointmentService = new AppointmentService();
            try {
                User client = (User) request.getSession().getAttribute("user");
                Procedure procedure = (Procedure) request.getSession().getAttribute("procedure");
                Integer employee = (Integer) request.getSession().getAttribute("selectedEmployee");
                LocalDate localDate = LocalDate.parse((String) request.getSession().getAttribute("selectedDate"));
                LocalTime localTime = LocalTime.parse(request.getParameter("timeSelect"));
                Appointment appointment = new Appointment();
                appointment.setUserId(client.getId());
                appointment.setDate(LocalDateTime.of(localDate,localTime));
                if (appointmentService.addAppointment(appointment, procedure, employee)) {
                    request.getSession().removeAttribute("selectedDate");
                    request.getSession().removeAttribute("selectedEmployee");
                    request.getSession().removeAttribute("procedure");
                    return PageEnum.APPOINTMENT.getPage();
                } else {
                    request.getSession().setAttribute("error", "an error occured while adding appointment");
                    return PageEnum.ERROR.getPage();
                }
            } catch (ServiceException e) {
                LOGGER.error("it is impossible to add appointment");
                request.getSession().setAttribute("error", "an error occured while adding appointment");
                return PageEnum.ERROR.getPage();
            }
        }
        Procedure procedure = (Procedure) request.getSession().getAttribute("procedure");
        if (procedure == null) {
            request.setAttribute("error", "Sorry, server error! " +
                    "We will try to solve this problem");
            return PageEnum.ERROR.getPage();
        }
        EmployeeService employeeService = new EmployeeService();
        try {
            List<User> employeeList = employeeService.employeesByProcedure(procedure);
            request.getSession().setAttribute("employeeList", employeeList);
            Integer selectedEmployee = null;
            try {
                selectedEmployee = Integer.parseInt(request.getParameter("employeeSelect"));
            } catch (NumberFormatException e){}
            if (selectedEmployee == null) {
                return PageEnum.APPOINTMENT_ADD.getPage();
            }
            request.getSession().setAttribute("selectedEmployee", selectedEmployee);
            String dateString = request.getParameter("dateSelect");
            if (dateString.equals("")) {
                return PageEnum.APPOINTMENT_ADD.getPage();
            }
            request.getSession().setAttribute("selectedDate", dateString);
            GraphicService graphicService = new GraphicService();
            LocalDate date = LocalDate.parse((String) request.getSession()
                    .getAttribute("selectedDate"));
            List<LocalTime> graphics = graphicService.graphicsByEmployee(selectedEmployee, date);
            request.getSession().setAttribute("graphics",graphics);
        } catch (ServiceException e) {
            LOGGER.error("service exception", e);
        }
        return PageEnum.APPOINTMENT_ADD.getPage();
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
