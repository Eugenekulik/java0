package by.training.beauty.controller.action.implementation.client;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.*;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.AppointmentService;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        String method = request.getParameter("method")!=null?"POST":"GET";
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        if(roles != null
                && roles.contains(new Role("client"))
                && request.getMethod().equals(method)) return true;
        return false;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getParameter("method");
        switch (method){
            case "create":
                break;
            case "delete":
                delete(request);
                break;
            case "update":
                break;
            default:
                get(request);

        }
        return PageEnum.APPOINTMENT.getPage();
    }


    private boolean delete(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("delete"));
        if (id != null) {
            try {
                ServiceFactory.getInstance()
                        .getAppointmentService()
                        .cancelAppointment(id);
                return true;
            } catch (ServiceException e) {
                LOGGER.warn(()->e.getMessage());
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
            Integer tab = null;
            try {
                tab = Integer.parseInt(request.getParameter("tab"));
            } catch (NumberFormatException e) {
            }
            if (tab == null) {
                tab = 1;
            }
            request.getSession().setAttribute("tab", tab);

        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get appointments");
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
        }
    }

}
