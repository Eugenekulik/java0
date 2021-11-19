package by.training.beauty_parlor.controller.action;

import by.training.beauty_parlor.domain.User;
import by.training.beauty_parlor.exception.ServiceException;
import by.training.beauty_parlor.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

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
        UserService userService = new UserService();
        if(request.getSession().getAttribute("userExist") != null){
            request.getSession().removeAttribute("userExist");
        }
        try {
            User user = userService.registrate(request);
            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user.getName());
                session.setAttribute("role", user.getRole());
                page = "/";
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
}
