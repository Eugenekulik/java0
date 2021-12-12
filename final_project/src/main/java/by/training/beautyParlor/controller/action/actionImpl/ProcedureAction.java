package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.domain.Category;
import by.training.beautyParlor.service.ServiceException;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.service.ProcedureService;
import by.training.beautyParlor.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * This class implement interface Action
 * and allows get page with procedures.
 *
 * @see Action
 * @see ProcedureService
 */

public class ProcedureAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureAction.class);
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("unknown","admin","client","employee");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String current = request.getParameter("current");
        try {
            ProcedureService procedureService =
                    ServiceFactory.getInstance().getProcedureService();
            List<Procedure> procedureList = procedureService.getProcedures();
            if(procedureList == null) {
                return PageEnum.MAIN.getPage();
            }
            if(current == null) {
                current = procedureList.get(0).getName();
            }
            Procedure procedure = procedureService.getProcedureByName(current);
            List<Category> categories = procedureService.getCategories();
            request.getSession().setAttribute("categories",categories);
            request.getSession().setAttribute("procedureList", procedureList);
            request.getSession().setAttribute("procedure", procedure);
            page = PageEnum.PROCEDURE.getPage();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get procedure list");
            page = PageEnum.MAIN.getPage();
        }
        return page;
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
