package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Role;
import by.training.beauty.service.ScheduleService;
import by.training.beauty.service.ProcedureService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * This class implement interface action which handles
 * administrator requests to add data.
 *
 * @see Action
 * @see ProcedureService
 * @see ScheduleService
 */

public class AdministrateAddAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AdministrateAddAction.class);

    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        return  roles != null
            && roles.contains(new Role("admin"))
                && request.getMethod().equals("POST");

    }

    @Override
    public String execute(HttpServletRequest request) {
        String tab = (String) request.getSession().getAttribute("activeTab");
        switch (tab) {
            case "3":
                ProcedureService procedureService =
                        ServiceFactory.getInstance().getProcedureService();
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
                try {
                    int employeeId = 0;
                    LocalDate date = null;
                    employeeId = Integer.parseInt(request.getParameter("employeeId"));
                    date = LocalDate.parse(request.getParameter("date"));
                    if (date != null && employeeId != 0) {
                        ServiceFactory.getInstance()
                                .getScheduleService()
                                .addSchedule(employeeId, date);
                    }
                } catch (ServiceException e) {
                    LOGGER.error("it is impossible to add schedule");
                } catch (NumberFormatException e) {
                    LOGGER.warn(e.getMessage());
                }
                break;
            default:
                break;
        }
        return "/administrate.html";
    }

}
