package by.training.beautyParlor.controller.action;

import by.training.beautyParlor.controller.action.actionImpl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class ActionFactory {
    private HttpServletRequest request;

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
            case "/login":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of("unknown");
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
            case "/administrate_add":
                switch ((String) request.getSession().getAttribute("activeTab")) {
                    case "3":
                        action = new ProcedureAddAction();
                        break;
                    case "4":
                        action  = new GraphicAddAction();
                }
                break;
            case "/registration":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of("unknown");
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
            case "/main":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of("unknown","admin","employee","client");
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
            case "/about":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of("client", "unknown","employee", "admin");
                    }

                    @Override
                    public String execute(HttpServletRequest request) {
                        return PageEnum.ABOUT.getPage();
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
        }
        return action;
    }
}
