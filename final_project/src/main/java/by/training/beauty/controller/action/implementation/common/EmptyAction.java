package by.training.beauty.controller.action.implementation.common;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * This class implement interface Action
 * and doesn't execute any service, only
 * return error page.
 */
public class EmptyAction implements Action {
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        return true;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return PageEnum.ERROR.getPage();
    }

}
