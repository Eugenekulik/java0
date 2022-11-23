package by.training.beauty.controller.action.implementation.admin;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.*;
import by.training.beauty.dto.AppointmentDto;
import by.training.beauty.dto.ProcedureDto;
import by.training.beauty.dto.ScheduleDto;
import by.training.beauty.service.AdministrateService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implement interface Action,
 * and allows get entities for further processing by the administrator
 *
 * Depending on the received parameter tab,
 * the specific method of AdministrateService is called.
 *
 * @see Action
 * @see AdministrateService
 */

public class AdministrateAction implements Action {
    //CONSTANTS
    private static final String ACTIVE_TAB = "activeTab";
    private static final String TAB = "tab";

    private static final Logger LOGGER  = LogManager.getLogger(AdministrateAction.class);
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        if(roles != null
                && roles.contains(new Role("admin"))
                && request.getMethod().equals("GET")) return true;
        return false;
    }


    @Override
    public String execute(HttpServletRequest request) {

        if (request.getParameter(TAB) != null) {
            request.getSession().setAttribute(ACTIVE_TAB, request.getParameter(TAB));
        } else if (request.getSession().getAttribute(ACTIVE_TAB) == null) {
            request.getSession().setAttribute(ACTIVE_TAB, "1");
        }
        int pageCount = 0;
        int paginationPage;
        try {
            paginationPage = Integer.parseInt(request.getParameter("paginationPage"));
        } catch (NumberFormatException e) {
            paginationPage = 1;
        }
        List<Entity> entities = null;
        try {
            switch ((String) request.getSession().getAttribute(ACTIVE_TAB)) {
                case "1":
                    pageCount = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .getPagecount(1);
                    List<User> users = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .administrateUsers(paginationPage);
                    List<Role> roles = ServiceFactory.getInstance()
                            .getRoleService()
                            .getAllRoles();
                    request.getSession().setAttribute("users", users);
                    request.getSession().setAttribute("allRoles", roles);
                    break;
                case "2":
                    pageCount = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .getPagecount(2);
                    List<AppointmentDto> appointmentDtoList = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .administrateAppointments(paginationPage);
                    request.getSession().setAttribute("appointments", appointmentDtoList);
                    break;
                case "3":
                    pageCount = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .getPagecount(3);
                    List<ProcedureDto> procedureDtoList = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .administrateProcedures(paginationPage);
                    request.getSession().setAttribute("procedures", procedureDtoList);
                    break;
                case "4":
                    pageCount = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .getPagecount(4);
                    List<ScheduleDto> scheduleDtoList = ServiceFactory.getInstance()
                            .getAdministrateService()
                            .administrateSchedules(paginationPage);
                    request.getSession().setAttribute("schedules", scheduleDtoList);
                    break;
                default:
                    break;
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to display information");
        }
        request.getSession().setAttribute("paginationPage", paginationPage);
        request.getSession().setAttribute("pageCount", pageCount);
        return PageEnum.ADMINISTRATE.getPage();
    }

}
