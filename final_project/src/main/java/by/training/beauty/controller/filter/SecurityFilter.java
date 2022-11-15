package by.training.beauty.controller.filter;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.ActionFactory;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement Filter and allows to keep track of the security. It's
 * it decides whether a given user has access to this page and whether the
 * request came from the correct method.
 */

public class SecurityFilter implements Filter {
    //CONSTANTS
    private static final String MESSAGE = "securityFilterMessage";

    private static final Logger LOGGER = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Action action = new ActionFactory(request).initCommand();
            if(action.isAllowed(request)) {
                chain.doFilter(servletRequest,servletResponse);
            }
            else {
                if(request.getSession().getAttribute("user") == null) {
                    response.sendRedirect(request.getContextPath() + "/login.html");
                }
                else {
                    request.setAttribute("error", "Access error!");
                    request.removeAttribute("action");
                    chain.doFilter(servletRequest,servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
