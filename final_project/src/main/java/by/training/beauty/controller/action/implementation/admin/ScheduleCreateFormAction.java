package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import by.training.beauty.service.EmployeeService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * This class implement interface Action
 * and allows get data required to add a schedule.
 *
 * @see Action
 * @see EmployeeService
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
        if(roles == null) return false;
        if(roles.contains(new Role("admin")) && request.getMethod().equals("POST")) return true;
        return false;
    }

    @Override
    public String execute(HttpServletRequest request) {
        EmployeeService employeeService =
                ServiceFactory.getInstance().getEmployeeService();
        try {
            List<User> employees = employeeService.employeeList();
            request.getSession().setAttribute("employeeList", employees);
        } catch (ServiceException e) {
            LOGGER.warn("it is impossible to get employee list");
        }
        return PageEnum.ADMINISTRATE_SCHEDULE_ADD.getPage();
    }

}
