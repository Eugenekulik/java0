package by.training.beauty.controller.action.implementation;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.*;
import by.training.beauty.service.AdministrateService;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
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
    public Set<String> getRoles() {
        return Set.of("admin");
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getParameter(TAB) != null) {
            request.getSession().setAttribute(ACTIVE_TAB, request.getParameter(TAB));
        } else if (request.getSession().getAttribute(ACTIVE_TAB) == null) {
            request.getSession().setAttribute(ACTIVE_TAB, "1");
        }
        AdministrateService administrateService = ServiceFactory.getInstance().getAdministrateService();
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
                    pageCount = administrateService.getPagecount(1);
                    List<User> users = administrateService.administrateUsers(paginationPage);
                    request.getSession().setAttribute("users", users);
                    break;
                case "2":
                    pageCount = administrateService.getPagecount(2);
                    entities = administrateService.administrateAppointments(paginationPage);
                    List<User> clients = entities.stream()
                            .filter(User.class::isInstance).map(User.class::cast)
                            .filter(user -> user.getRole().equals("client")).collect(Collectors.toList());
                    List<User> employees = entities.stream()
                            .filter(User.class::isInstance).map(User.class::cast)
                            .filter((user -> user.getRole().equals("employee"))).collect(Collectors.toList());
                    List<Appointment> appointments = entities.stream()
                            .filter(Appointment.class::isInstance)
                            .map(Appointment.class::cast).collect(Collectors.toList());
                    List<Procedure> procedures = entities.stream()
                            .filter(Procedure.class::isInstance)
                            .map(Procedure.class::cast).collect(Collectors.toList());
                    request.getSession().setAttribute("clients", clients);
                    request.getSession().setAttribute("employees", employees);
                    request.getSession().setAttribute("appointments", appointments);
                    request.getSession().setAttribute("procedures", procedures);
                    break;
                case "3":
                    pageCount = administrateService.getPagecount(3);
                    entities = administrateService.administrateProcedures(paginationPage);
                    List<Procedure> procedures1 = entities.stream()
                            .filter(Procedure.class::isInstance)
                            .map(Procedure.class::cast)
                            .collect(Collectors.toList());
                    List<Category> categories = entities.stream()
                            .filter(Category.class::isInstance)
                            .map(Category.class::cast)
                            .collect(Collectors.toList());
                    request.getSession().setAttribute("categories", categories);
                    request.getSession().setAttribute("procedures", procedures1);
                    break;
                case "4":
                    pageCount = administrateService.getPagecount(4);
                    entities = administrateService.administrateSchedules(paginationPage);
                    List<User> employees1 = entities.stream()
                            .filter(User.class::isInstance)
                            .map(User.class::cast)
                            .collect(Collectors.toList());
                    List<Schedule> schedules = entities.stream()
                            .filter(Schedule.class::isInstance)
                            .map(Schedule.class::cast)
                            .collect(Collectors.toList());
                    request.getSession().setAttribute("schedules", schedules);
                    request.getSession().setAttribute("employees" ,employees1);
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

    @Override
    public String getMethod() {
        return "GET";
    }
}
