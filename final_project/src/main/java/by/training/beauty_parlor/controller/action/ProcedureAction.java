package by.training.beauty_parlor.controller.action;

import by.training.beauty_parlor.exception.ServiceException;
import by.training.beauty_parlor.domain.Procedure;
import by.training.beauty_parlor.service.ProcedureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class ProcedureAction implements Action{
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
            ProcedureService procedureService = new ProcedureService();
            List<String> procedureList = procedureService.getProceduresName();
            if(procedureList == null) {
                return "/";
            }
            if(current == null) {
                current = procedureList.get(0);
            }
            Procedure procedure = procedureService.getProcedureByName(current);
            List<String> categories = procedureService.getCategoriesName();
            request.getSession().setAttribute("procedureList", procedureList);
            request.getSession().setAttribute("procedure", procedure);
            request.getSession().setAttribute("categoryList", categories);
            page = PageEnum.PROCEDURE.getPage();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to autorizate");
            page ="/";
        }
        return page;
    }
}
