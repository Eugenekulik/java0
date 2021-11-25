package by.training.beauty_parlor.controller.action.actionImpl;

import by.training.beauty_parlor.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class LogoutAction implements Action {
    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("admin","client","employee");
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.removeAttribute("role");
            session.removeAttribute("user");
        }
        return "/main.html";
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
