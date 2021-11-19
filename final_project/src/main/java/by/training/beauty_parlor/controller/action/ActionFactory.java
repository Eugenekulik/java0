package by.training.beauty_parlor.controller.action;

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
                        return Set.of("unknown", "client", "admin", "employee");
                    }

                    @Override
                    public String execute(HttpServletRequest request) {
                        return PageEnum.LOGIN.getPage();
                    }
                };
                break;
            case "/registration":
                action = new Action() {
                    @Override
                    public boolean isRedirect() {
                        return false;
                    }

                    @Override
                    public Set<String> getRoles() {
                        return Set.of("unknown", "admin", "client", "employee");
                    }

                    @Override
                    public String execute(HttpServletRequest request) {
                        return PageEnum.REGISTRATION.getPage();
                    }
                };
                break;
            case "/index":
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
                        return "/";
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
        }
        return action;
    }
}
