package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Procedure;
import by.training.beauty.service.ProcedureService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


public class AdministrateProcedureAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateProcedureAction.class);

    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("admin");
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
        return false;
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
