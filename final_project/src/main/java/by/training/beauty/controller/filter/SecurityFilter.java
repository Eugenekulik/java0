package by.training.beauty.controller.filter;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class implement Filter and allows to keep track of the security. It's
 * it decides whether a given user has access to this page and whether the
 * request came from the correct method.
 */

public class SecurityFilter implements Filter {
    //CONSTANTS

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
                    LOGGER.warn(()->"Access was denied: " + request.getRequestURI());
                    response.sendRedirect(request.getContextPath() + "/login.html");
                }
                else {
                    LOGGER.warn(()->"Access was denied: " + request.getRequestURI() +
                            "for user: " +  request.getSession().getAttribute("user"));
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
