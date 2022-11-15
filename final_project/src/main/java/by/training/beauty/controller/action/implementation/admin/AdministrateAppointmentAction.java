package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Appointment;
import by.training.beauty.service.AppointmentService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class AdministrateAppointmentAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateAppointmentAction.class);

    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("admin");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getParameter("method");
        switch (method){
            case "create":
                create(request);
            case "update":
                update(request);
            case "delete":
                delete(request);
        }
        return "/administrate.html";
    }

    private boolean delete(HttpServletRequest request) {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("appointmentId"));
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
        }
        if (id != null) {
            try {
                AppointmentService appointmentService =
                        ServiceFactory.getInstance().getAppointmentService();
                appointmentService.deleteAppointment(id);
                return true;
            } catch (ServiceException e) {
                LOGGER.warn(String.format("it is impossible to delete user by id: %d", id));
            }
        }
        return false;
    }

    private boolean update(HttpServletRequest request) {
        Appointment appointment = new Appointment();
        try {
            appointment.setId(Integer.parseInt(request.getParameter("appointmentId")));
            List<Appointment> appointments = (List<Appointment>) request.getSession().getAttribute("appointments");
            for (Appointment a: appointments) {
                if(appointment.getId() == a.getId()) {
                    appointment = a;
                    break;
                }
            }
            appointment.setPrice(Double.valueOf(request.getParameter("appointmentPrice")));
            appointment.setStatus(Integer.parseInt(request.getParameter("selectStatus")));
            AppointmentService appointmentService =
                    ServiceFactory.getInstance().getAppointmentService();
            appointmentService.updateAppointment(appointment);
            return true;
        } catch (ServiceException e) {
            LOGGER.warn(String.format("it is impossible to update appointment with id: %d", appointment.getId()));
        } catch (NumberFormatException e) {
            LOGGER.warn(e);
        }
        return false;
    }

    private boolean create(HttpServletRequest request) {
        return false;
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
