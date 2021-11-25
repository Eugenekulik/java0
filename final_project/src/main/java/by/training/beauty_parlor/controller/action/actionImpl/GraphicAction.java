package by.training.beauty_parlor.controller.action.actionImpl;

import by.training.beauty_parlor.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class GraphicAction implements Action {
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("client");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        return null;
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
