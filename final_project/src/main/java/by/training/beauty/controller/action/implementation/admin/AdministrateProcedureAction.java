package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Role;
import by.training.beauty.service.ProcedureService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class AdministrateProcedureAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateProcedureAction.class);

    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        return roles != null
                && roles.contains(new Role("admin"))
                && request.getMethod().equals("POST");

    }

    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getParameter("method");
        switch (method){
            case "create":
                create(request);
                break;
            case "update":
                update(request);
                break;
            case "delete":
                delete(request);
                break;
            default:
                LOGGER.warn(()->"Unsupported operation with method name: " + method);
        }
        return "/administrate.html";
    }

    private boolean update(HttpServletRequest request) {
        Procedure procedure = new Procedure();
        try {
            procedure.setId(Integer.parseInt(request.getParameter("procedureId")));
            List<Procedure> procedures = (List<Procedure>) request.getSession().getAttribute("procedures");
            for (Procedure p: procedures) {
                if(procedure.getId() == p.getId()) {
                    procedure = p;
                    break;
                }
            }
            procedure.setElapsedTime(Integer
                    .parseInt(request.getParameter("procedureElapsedTime")));
            procedure.setName(request.getParameter("procedureName"));
            procedure.setDescription(request.getParameter("description"));
            ProcedureService procedureService =
                    ServiceFactory.getInstance().getProcedureService();
            procedureService.updateProcedure(procedure);
            return true;
        } catch (ServiceException e) {
            LOGGER.warn(String.format("it is impossible to update appointment with id: %d", procedure.getId()));
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
        }
        return false;
    }

    private boolean delete(HttpServletRequest request) {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("procedureId"));
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
        }
        if (id != null) {
            try {
                ProcedureService procedureService =
                        ServiceFactory.getInstance().getProcedureService();
                procedureService.deleteProcedure(id);
                return true;
            } catch (ServiceException e) {
                LOGGER.warn(String.format("it is impossible to delete procedure by id: %d", id));
            }
        }
        return false;
    }

    private boolean create(HttpServletRequest request) {
        ProcedureService procedureService =
                ServiceFactory.getInstance().getProcedureService();
        try {
            Procedure procedure = new Procedure();
            procedure.setName(request.getParameter("name"));
            procedure.setDescription(request.getParameter("description"));
            procedure.setCategoryId(Integer.parseInt(request.getParameter("category")));
            procedure.setElapsedTime(Integer.parseInt(request.getParameter("elapsedTime")));
            procedureService.addProcedure(procedure);
            return true;
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to add procedure");
        }
        return false;
    }

}
