package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.domain.Appointment;
import by.training.beautyParlor.domain.Entity;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.service.ServiceException;
import by.training.beautyParlor.service.AppointmentService;
import by.training.beautyParlor.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
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
            AppointmentService appointmentService =
                    ServiceFactory.getInstance().getAppointmentService();
            if(request.getParameter("delete") != null) {
                Integer id = null;
                try {
                    id = Integer.parseInt(request.getParameter("delete"));
                } catch (NumberFormatException e){}
                if(id != null) {
                    appointmentService.deleteAppointment(id);
                }
            }
            User user = (User)request.getSession().getAttribute("user");
            List<Entity> entities = appointmentService.usersAppointment(user);
            List<Appointment> appointments = entities.stream()
                    .filter(entity -> entity instanceof Appointment)
                    .map(entity -> {return (Appointment)entity;})
                    .collect(Collectors.toList());
            List<User> employees = entities.stream()
                    .filter(entity -> entity instanceof User)
                    .map(entity -> {return (User)entity;})
                    .collect(Collectors.toList());
            request.getSession().setAttribute("employees",employees);
            request.getSession().setAttribute("appointments",appointments);
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
