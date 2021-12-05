package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.domain.Category;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.service.ProcedureService;
import by.training.beautyParlor.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

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
        ProcedureService procedureService = new ProcedureService();
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
