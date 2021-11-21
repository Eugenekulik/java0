package by.training.beauty_parlor.controller.filter;

import by.training.beauty_parlor.controller.action.Action;
import by.training.beauty_parlor.controller.action.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean checkRole(HttpServletRequest request, Action action){
        HttpSession session = request.getSession(false);
        String userName = "unknown";
        String role = null;
        if (session != null) {
            userName = (String) session.getAttribute("user");
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
            LOGGER.info(String.format("Trying of %s to forbidden to resource", userName));
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
