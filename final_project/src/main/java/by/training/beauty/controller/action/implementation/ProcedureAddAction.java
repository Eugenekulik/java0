package by.training.beauty.controller.action.implementation;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.Category;
import by.training.beauty.service.ProcedureService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * This class implement interface Action
 * and allows adding procedure.
 *
 * @see Action
 * @see ProcedureService
 */

public class ProcedureAddAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureAddAction.class);
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
        ProcedureService procedureService =
                ServiceFactory.getInstance().getProcedureService();
        try {
            List<Category> categories = procedureService.getCategories();
            request.getSession().setAttribute("categories",categories);
        } catch (ServiceException e) {
            LOGGER.warn("it is impossible to get categories");
        }
        return PageEnum.ADMINISTRATE_PROCEDURE_ADD.getPage();
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
