package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class implement interface Action
 * and allows get data required to add a schedule.
 *
 * @see Action
 * @see UserService
 */

public class ScheduleCreateFormAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleCreateFormAction.class);
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        return roles == null
                && roles.contains(new Role("admin"))
                && request.getMethod().equals("GET");
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService =
                ServiceFactory.getInstance().getUserService();
        try {
            List<User> employees = userService.employeeList();
            request.getSession().setAttribute("employeeList", employees);
        } catch (ServiceException e) {
            LOGGER.warn("it is impossible to get employee list");
        }
        return PageEnum.ADMINISTRATE_SCHEDULE_ADD.getPage();
    }

}
