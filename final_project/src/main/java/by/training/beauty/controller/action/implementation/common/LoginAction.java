package by.training.beauty.controller.action.implementation.common;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.User;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.ServiceFactory;
import by.training.beauty.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * This class implement interface Action
 * and allows authorized user.
 *
 * @see Action
 * @see UserService
 */

public class LoginAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(LoginAction.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String ERROR = "errorLoginPassMessage";
    private boolean redirect = true;
    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        return request.getSession().getAttribute("roles")==null
                && request.getMethod().equals("POST");
    }


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        if(request.getSession().getAttribute(ERROR) != null){
            request.getSession().removeAttribute(ERROR);
        }
        try {
            UserService userService =
                    ServiceFactory.getInstance().getUserService();
            User user = userService.login(login,password);
            if(user !=null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("roles", user.getRoles());
                page = "/main.html";
            }
            else {
                request.getSession().setAttribute(ERROR, "Сheck if the username or password is entered correctly");
                page ="/login.html";
            }
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to authorize user");
            page ="/login.html";
        }
        return page;
    }

}
