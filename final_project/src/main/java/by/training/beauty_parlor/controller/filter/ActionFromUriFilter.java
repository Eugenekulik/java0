package by.training.beauty_parlor.controller.filter;

import com.sun.net.httpserver.HttpContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class ActionFromUriFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(ActionFromUriFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String contextPath = request.getContextPath();
            String uri = request.getRequestURI();
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf(".");
            String actionName;
            if(endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            }
            else {
                actionName = uri.substring(beginAction);
            }
            request.setAttribute("action", actionName);
            request.getSession().setAttribute("action", actionName);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
