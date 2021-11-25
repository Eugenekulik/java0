package by.training.beauty_parlor.controller.action.actionImpl;

import by.training.beauty_parlor.controller.action.Action;
import by.training.beauty_parlor.controller.action.PageEnum;
import by.training.beauty_parlor.domain.Appointment;
import by.training.beauty_parlor.service.EmployeeService;
import by.training.beauty_parlor.service.ServiceException;
import by.training.beauty_parlor.service.AppointmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class AppointmentAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentAction.class);
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
        String page = null;
        try {
            AppointmentService appointmentService = new AppointmentService();
            appointmentService.usersAppointment(request);
            Integer tab = null;
            try {
                tab = Integer.parseInt(request.getParameter("tab"));
            } catch (NumberFormatException e) {}
            if(tab != null) {
                request.setAttribute("tab",tab);
            }
            page = PageEnum.APPOINTMENT.getPage();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to autorizate");
            page ="/login.html";
        }
        return page;
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
