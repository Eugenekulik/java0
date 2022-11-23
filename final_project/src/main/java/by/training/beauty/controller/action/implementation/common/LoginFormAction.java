package by.training.beauty.controller.action.implementation.common;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;

import javax.servlet.http.HttpServletRequest;

public class LoginFormAction implements Action {
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        return request.getSession().getAttribute("roles")==null
                && request.getMethod().equals("GET");
    }


    @Override
    public String execute(HttpServletRequest request) {
        return PageEnum.LOGIN.getPage();
    }

}
