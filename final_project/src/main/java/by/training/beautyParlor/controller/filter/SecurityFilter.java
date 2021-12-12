package by.training.beautyParlor.controller.filter;

import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.ActionFactory;
import by.training.beautyParlor.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class implement Filter and allows to keep track of the security. It's
 * it decides whether a given user has access to this page and whether the
 * request came from the correct method.
 */

public class SecurityFilter implements Filter {
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
            if(checkRole(request, action) && checkMethod(request, action)) {
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

    private boolean checkRole(HttpServletRequest request, Action action){
        HttpSession session = request.getSession(false);
        User user = new User();
        user.setName("unknown");
        String role = null;
        if (session != null) {
            user  = ((User) session.getAttribute("user"));
            role = (String) session.getAttribute("role");
            if(role == null) {
                role = "unknown";
            }
            String errorMessage = (String) session.getAttribute("securityFilterMessage");
            if (errorMessage != null) {
                request.setAttribute("message",errorMessage);
                session.removeAttribute("securityFilterMessage");
            }
        }
        if (action.getRoles().contains(role)) {
            return true;
        } else {
            LOGGER.info(String.format("Trying of %s to forbidden to resource", user.getName()));
            if (session != null) {
                session.getAttribute("securityFilterMessage");
            }
            return false;
        }

    }

    private boolean checkMethod(HttpServletRequest request, Action action){
        String method = request.getMethod();
        if(request.getMethod().equals(action.getMethod())) {
            return true;
        } else {
            LOGGER.info(String.format("An attempt to send a request using the wrong method"));
            HttpSession session = request.getSession();
            if (session != null) {
                session.getAttribute("securityFilterMessage");
            }
            return false;
        }
    }
}
