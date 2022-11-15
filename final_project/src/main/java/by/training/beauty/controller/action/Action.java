package by.training.beauty.controller.action;

import by.training.beauty.domain.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
     * This method allows you to find out if the request is allowed.
     * @return boolean if allowed.
     */
    boolean isAllowed(HttpServletRequest request);

    /**
     * It is method is main which calls the necessery services.
     * @param request HttpServletRequest
     * @return String page path.
     */
    String execute(HttpServletRequest request);

}
