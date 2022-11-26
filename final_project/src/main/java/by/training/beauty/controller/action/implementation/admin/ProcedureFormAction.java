package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.Category;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Role;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProcedureFormAction implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ProcedureFormAction.class);
    private boolean redirect = false;
    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        return roles != null
                && roles.contains(new Role("admin"))
                && request.getMethod().equals("GET");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getParameter("method");
        String page = null;
        switch (method){
            case "create":
                page = create(request);
                break;
            case "update":
                page = update(request);
        }
        return page;
    }

    private String update(HttpServletRequest request) {
        try {
            List<Category> categories = ServiceFactory.getInstance()
                    .getCategoryService().getAllCategories();
            request.setAttribute("categories", categories);
            int id = Integer.parseInt(request.getParameter("id"));
            Procedure procedure = ServiceFactory.getInstance()
                    .getProcedureService().getProcedureById(id);
            request.setAttribute("procedure", procedure);
            return PageEnum.PROCEDURE_FORM_UPDATE.getPage();
        } catch (NumberFormatException e){
            LOGGER.warn("invalid procedure id");
        } catch (ServiceException e){
            LOGGER.warn("Error occurred while trying to find procedure");
        }
        return null;
    }

    private String create(HttpServletRequest request) {
        List<Category> categories = ServiceFactory.getInstance()
                .getCategoryService().getAllCategories();
        request.setAttribute("categories", categories);
        return PageEnum.PROCEDURE_FORM_CREATE.getPage();
    }
}
