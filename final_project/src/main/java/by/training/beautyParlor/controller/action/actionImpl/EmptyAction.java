package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class EmptyAction implements Action {
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("unknown","client","employee","admin");
    }

    @Override
    public String execute(HttpServletRequest request) {
        return PageEnum.ERROR.getPage();
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
