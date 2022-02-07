package by.training.beauty.controller.action;

import by.training.beauty.controller.action.implementation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * This class allows init action by the request.
 */

public class ActionFactory {
    private HttpServletRequest request;
    private static final String UNKNOWN = "unknown";

    public ActionFactory(HttpServletRequest request) {
        this.request = request;
    }

    public Action initCommand() {
        Action action = null;
        String commandType = (String) request.getAttribute("action");
        if (commandType == null) {
            return new EmptyAction();
        }
        switch (commandType) {
            //This action allows getting login page.
            case "/login":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of(UNKNOWN);
                    }

                    @Override
                    public String execute(HttpServletRequest request) {
                        return PageEnum.LOGIN.getPage();
                    }

                    @Override
                    public String getMethod() {
                        return "GET";
                    }
                };
                break;
                // This action allows getting administrate_add page.
            case "/administrate_add":
                switch ((String) request.getSession().getAttribute("activeTab")) {
                    case "3":
                        action = new ProcedureAddAction();
                        break;
                    case "4":
                        action  = new ScheduleAddAction();
                        break;
                    default: action = new EmptyAction();
                }
                break;
                //This action allows getting registration page.
            case "/registration":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of(UNKNOWN);
                    }

                    @Override
                    public String execute(HttpServletRequest request) {
                        return PageEnum.REGISTRATION.getPage();
                    }

                    @Override
                    public String getMethod() {
                        return "GET";
                    }
                };
                break;
                //This action allows getting main page.
            case "/main":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of(UNKNOWN,"admin","employee","client");
                    }

                    @Override
                    public String execute(HttpServletRequest request) {
                        return PageEnum.MAIN.getPage();
                    }

                    @Override
                    public String getMethod() {
                        return "GET";
                    }
                };
                break;
            case "/login_submit":
                action = new LoginAction();
                break;
            case "/logout":
                action = new LogoutAction();
                break;
            case "/registration_submit":
                action = new RegistrationAction();
                break;
            case "/procedure":
                action = new ProcedureAction();
                break;
            case "/appointment":
                action = new AppointmentAction();
                break;
            case "/appointment_add":
                action = new AddAppointmentAction();
                break;
            case "/administrate":
                action = new AdministrateAction();
                break;
            case "/changeData":
                action = new AdministrateChangeAction();
                break;
            case  "/administrate_add_submit":
                action = new AdministrateAddAction();
                break;
            case "/schedule":
                action = new ScheduleAction();
                break;
            case "/score_add":
                action = new AddScoreAction();
                break;
            default: action = new EmptyAction();
        }
        return action;
    }
}
