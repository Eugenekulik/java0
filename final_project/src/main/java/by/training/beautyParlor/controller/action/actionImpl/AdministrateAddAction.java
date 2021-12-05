package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.service.GraphicService;
import by.training.beautyParlor.service.ProcedureService;
import by.training.beautyParlor.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Set;

public class AdministrateAddAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateAddAction.class);
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
        String tab = (String) request.getSession().getAttribute("activeTab");
        switch (tab){
            case "3":
                ProcedureService procedureService = new ProcedureService();
                try {
                    Procedure procedure = new Procedure();
                    procedure.setName(request.getParameter("name"));
                    procedure.setDescription(request.getParameter("description"));
                    procedure.setCategoryId(Integer.parseInt(request.getParameter("categorySelect")));
                    procedure.setElapsedTime(Integer.parseInt(request.getParameter("elapsedTime")));
                    procedureService.addProcedure(procedure);
                } catch (ServiceException e) {
                    LOGGER.error("it is impossible to add procedure");
                }
                break;
            case "4":
                GraphicService graphicService = new GraphicService();
                try {
                    int employeeId = 0;
                    LocalDate date = null;
                    try {
                        employeeId = Integer.parseInt(request.getParameter("employeeId"));
                        date = LocalDate.parse(request.getParameter("date"));
                    } catch (NumberFormatException e) {}
                    if(date != null && employeeId != 0) {
                        graphicService.addGraphic(employeeId, date);
                    }
                } catch (ServiceException e) {
                    LOGGER.error("it is impossible to add graphic");
                }
        }
        return "/administrate.html";
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
