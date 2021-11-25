package by.training.beauty_parlor.controller.action.actionImpl;

import by.training.beauty_parlor.controller.action.Action;
import by.training.beauty_parlor.controller.action.PageEnum;
import by.training.beauty_parlor.domain.Procedure;
import by.training.beauty_parlor.domain.User;
import by.training.beauty_parlor.service.ServiceException;
import by.training.beauty_parlor.service.AppointmentService;
import by.training.beauty_parlor.service.EmployeeService;
import by.training.beauty_parlor.service.GraphicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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
                if (appointmentService.addAppointment(request)) {
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
            employeeService.employeesByProcedure(request);
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
            graphicService.graphicsByEmployee(request);
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
