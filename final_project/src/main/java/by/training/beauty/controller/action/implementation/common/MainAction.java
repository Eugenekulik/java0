package by.training.beauty.controller.action.implementation.common;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class MainAction implements Action {
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
}
