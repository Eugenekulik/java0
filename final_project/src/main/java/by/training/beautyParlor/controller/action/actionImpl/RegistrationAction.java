package by.training.beautyParlor.controller.action.actionImpl;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.service.ServiceException;
import by.training.beautyParlor.service.ServiceFactory;
import by.training.beautyParlor.service.UserService;
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
    private static final Logger LOGGER = LogManager.getLogger(RegistrationAction.class);
    private boolean redirect = true;
    @Override
    public boolean isRedirect() {
        return redirect;
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
        if(request.getSession().getAttribute("userExist") != null){
            request.getSession().removeAttribute("userExist");
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
                request.getSession().setAttribute("userExist","User with this login exists");
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
