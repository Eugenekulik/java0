package by.training.beautyParlor.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ReRequestProtectionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String lastRequest = (String) request.getSession().getAttribute("lastRequest");
        String actualRequest = request.getRequestURI();
        if(!lastRequest.equals(actualRequest)) {
            doFilter(servletRequest,servletResponse,filterChain);
        }
        else {

        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
