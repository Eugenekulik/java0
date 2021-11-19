package by.training.beauty_parlor.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse responce, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpServletResponse responce1 =(HttpServletResponse) responce;
        responce1.setCharacterEncoding("UTF-8");
        responce1.setHeader("cache-control", "no-cache");
        responce1.setHeader("Pragma","no-cache");
        responce1.setDateHeader("Expires", 0);
        chain.doFilter(request,responce);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
