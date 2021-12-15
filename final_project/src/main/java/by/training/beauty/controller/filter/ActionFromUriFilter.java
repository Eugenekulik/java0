package by.training.beauty.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This class implement Filter
 * and allows getting action from request.
 */

public class ActionFromUriFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
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
