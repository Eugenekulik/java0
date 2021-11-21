package by.training.beauty_parlor.controller.action.actionImpl.get;

import by.training.beauty_parlor.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class EmptyAction implements Action {

    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("unknown","admin", "client", "employee");
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "/main.html";
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
