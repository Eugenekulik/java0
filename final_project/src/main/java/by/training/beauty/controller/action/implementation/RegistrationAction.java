package by.training.beauty.controller.action.implementation;

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
 * and give registration opportunity.
 *
 * @see Action
 * @see UserService
 */

public class RegistrationAction implements Action {
    //CONSTANTS
    private static final String EXIST = "userExist";

    private static final Logger LOGGER = LogManager.getLogger(RegistrationAction.class);
    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public Set<String> getRoles() {
        return Set.of("unknown","client","admin","employee");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        UserService userService =
                ServiceFactory.getInstance().getUserService();
        if(request.getSession().getAttribute(EXIST) != null){
            request.getSession().removeAttribute(EXIST);
        }
        try {
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            user.setPhone(request.getParameter("phone"));
            user = userService.registrate(user);
            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                page = "/main.html";
            }
            else {
                request.getSession().setAttribute(EXIST,"User with this login exists");
                page = "/registration.html";
            }
        } catch (ServiceException e) {
            LOGGER.warn("It is impossible to register user");
            page = "/registration.html";
        }
        return page;
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
