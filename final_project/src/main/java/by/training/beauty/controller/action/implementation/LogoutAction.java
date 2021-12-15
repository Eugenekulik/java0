package by.training.beauty.controller.action.implementation;

import by.training.beauty.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * This class implement interface Action
 * and allows logout user.
 *
 * @see Action
 */

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
            session.invalidate();
        }
        return "/main.html";
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
