package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Role;
import by.training.beauty.service.ScheduleService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class AdministrateScheduleAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateScheduleAction.class);

    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        if(roles == null) return false;
        if(roles.contains(new Role("admin")) && request.getMethod().equals("POST")) return true;
        return false;
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
            id = Integer.parseInt(request.getParameter("scheduleId"));
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
        }
        if (id != null) {
            try {
                ScheduleService scheduleService =
                        ServiceFactory.getInstance().getScheduleService();
                scheduleService.deleteschedule(id);
                return true;
            } catch (ServiceException e) {
                LOGGER.warn(String.format("it is impossible to delete schedule by id: %d", id));
            }
        }
        return false;
    }

    private boolean update(HttpServletRequest request) {
        return false;
    }

    private boolean create(HttpServletRequest request) {
        return false;
    }

}
