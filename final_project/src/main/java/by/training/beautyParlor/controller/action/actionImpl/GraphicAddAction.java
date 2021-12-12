package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.service.EmployeeService;
import by.training.beautyParlor.service.ServiceException;
import by.training.beautyParlor.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * This class implement interface Action
 * and allows get data required to add a graphic.
 *
 * @see Action
 * @see EmployeeService
 */

public class GraphicAddAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(GraphicAddAction.class);
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("admin");
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
        return PageEnum.ADMINISTRATE_GRAPHIC_ADD.getPage();
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
