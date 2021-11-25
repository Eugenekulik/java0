package by.training.beauty_parlor.controller.action.actionImpl;

import by.training.beauty_parlor.controller.action.Action;
import by.training.beauty_parlor.controller.action.PageEnum;
import by.training.beauty_parlor.service.AdministrateService;
import by.training.beauty_parlor.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class AdministrateAction implements Action {
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

        if(request.getParameter("tab") != null) {
            request.getSession().setAttribute("activeTab", request.getParameter("tab"));
        }
        else {
            request.getSession().setAttribute("activeTab", "1");
        }
        AdministrateService administrateService = new AdministrateService();
        switch ((String)request.getSession().getAttribute("activeTab")){
            case "1":
                administrateService.administrateUsers(request);
                break;
            case "2":
                administrateService.administrateAppointments(request);
                break;
            case "3":
                administrateService.administrateProcedures(request);
                break;
            case "4":
                administrateService.administrateGraphics(request);
                break;
        }
        return PageEnum.ADMINISTRATE.getPage();
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
