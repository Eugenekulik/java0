package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.dao.CategoryDao;
import by.training.beautyParlor.domain.*;
import by.training.beautyParlor.service.AdministrateService;
import by.training.beautyParlor.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.LinkOption;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdministrateAction implements Action {
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
        String page;

        if (request.getParameter("tab") != null) {
            request.getSession().setAttribute("activeTab", request.getParameter("tab"));
        } else if (request.getSession().getAttribute("activeTab") == null) {
            request.getSession().setAttribute("activeTab", "1");
        }
        AdministrateService administrateService = new AdministrateService();
        int pageCount = 0;
        int paginationPage;
        try {
            paginationPage = Integer.parseInt(request.getParameter("paginationPage"));
        } catch (NumberFormatException e) {
            paginationPage = 1;
        }
        List<Entity> entities = null;
        try {
            switch ((String) request.getSession().getAttribute("activeTab")) {
                case "1":
                    pageCount = administrateService.getPagecount(1);
                    List<User> users = administrateService.administrateUsers(paginationPage);
                    request.getSession().setAttribute("users", users);
                    break;
                case "2":
                    pageCount = administrateService.getPagecount(2);
                    entities = administrateService.administrateAppointments(paginationPage);
                    List<User> clients = entities.stream()
                            .filter(entity -> entity instanceof User).map(entity -> {
                                return (User) entity;
                            })
                            .filter(user -> user.getRole().equals("client")).collect(Collectors.toList());
                    List<User> employees = entities.stream()
                            .filter(entity -> entity instanceof User).map(entity -> {
                                return (User) entity;
                            })
                            .filter((user -> user.getRole().equals("employee"))).collect(Collectors.toList());
                    List<Appointment> appointments = entities.stream()
                            .filter(entity -> entity instanceof Appointment)
                            .map(entity -> {
                                return (Appointment) entity;
                            }).collect(Collectors.toList());
                    List<Procedure> procedures = entities.stream()
                            .filter(entity -> entity instanceof Procedure)
                            .map(entity -> {
                                return (Procedure) entity;
                            }).collect(Collectors.toList());
                    request.getSession().setAttribute("clients", clients);
                    request.getSession().setAttribute("employees", employees);
                    request.getSession().setAttribute("appointments", appointments);
                    request.getSession().setAttribute("procedures", procedures);
                    break;
                case "3":
                    pageCount = administrateService.getPagecount(3);
                    entities = administrateService.administrateProcedures(paginationPage);
                    List<Procedure> procedures1 = entities.stream()
                            .filter(entity -> entity instanceof Procedure)
                            .map(entity -> {
                                return (Procedure) entity;
                            })
                            .collect(Collectors.toList());
                    List<Category> categories = entities.stream()
                            .filter(entity -> entity instanceof Category)
                            .map(entity -> {
                                return (Category) entity;
                            })
                            .collect(Collectors.toList());
                    request.getSession().setAttribute("categories", categories);
                    request.getSession().setAttribute("procedures", procedures1);
                    break;
                case "4":
                    pageCount = administrateService.getPagecount(4);
                    entities = administrateService.administrateGraphics(paginationPage);
                    List<User> employees1 = entities.stream()
                            .filter(entity -> entity instanceof User)
                            .map(entity -> {
                                return (User) entity;
                            })
                            .collect(Collectors.toList());
                    List<Graphic> graphics = entities.stream()
                            .filter(entity -> entity instanceof Graphic)
                            .map(entity -> {
                                return (Graphic) entity;
                            })
                            .collect(Collectors.toList());
                    request.getSession().setAttribute("graphics", graphics);
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
