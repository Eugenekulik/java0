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
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest request1 = (HttpServletRequest) request;
            HttpServletResponse response1 = (HttpServletResponse) response;
            Action command = new ActionFactory(request1).initCommand();
            HttpSession session = request1.getSession(false);
            String userName = "unknown";
            String role = "unknown";
            if (session != null) {
                userName = (String) session.getAttribute("user");
                role = (String) session.getAttribute("role");
                String errorMessage = (String) session.getAttribute("securityFilterMessage");
                if (errorMessage != null) {
                    request1.setAttribute("message",errorMessage);
                    session.removeAttribute("securityFilterMessage");
                }
            }
            if (command.getRoles().contains(role)) {
                chain.doFilter(request, response);
            } else {
                LOGGER.info(String.format("Trying of %s to forbidden to resource", userName));
                if (session != null) {
                    session.getAttribute("securityFilterMessage");
                }
            }


        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
