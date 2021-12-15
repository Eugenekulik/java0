package by.training.beauty.controller.action;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


/**
 * This interface give opportunity to execute any command
 * depending on the client's request.
 */
public interface Action {
    /**
     * This method allows figure out what controller should do with page:
     * forward or redirect.
     *
     * @return boolean true if redirect, or false if forward.
     */
    boolean isRedirect();

    /**
     * This method allows figure out users with which roles
     * access to the command is allowed.
     * @return Set<String> contains all roles which have access.
     */
    Set<String> getRoles();

    /**
     * It is method is main which calls the necessery services.
     * @param request HttpServletRequest
     * @return String page path.
     */
    String execute(HttpServletRequest request);

    /**
     * This method allows figure out what query method are allowed.
     * @return String method name(GET, POST or another)
     */
    String getMethod();
}
