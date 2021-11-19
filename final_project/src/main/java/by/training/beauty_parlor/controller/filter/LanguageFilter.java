package by.training.beauty_parlor.controller.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse responce =(HttpServletResponse) servletResponse;
        String select = request.getParameter("language");
        Cookie lang = null;
        if(select ==null) {
            for (Cookie c:request.getCookies()) {
            if(c.getName().equals("language")){
                lang = c;
            }
        }
            if(lang == null) {
                lang = new Cookie("language", "en_US");
            }

        } else {
            lang = new Cookie("language",select);
        }
        String[] localeArray = lang.getValue().split("_");
        Locale locale = new Locale(localeArray[0],localeArray[1]);
        ResourceBundle bundle = ResourceBundle.getBundle("text",locale);
        responce.addCookie(lang);
        request.setAttribute("text", bundle);
        switch(localeArray[0]){
            case "en":
                request.setAttribute("selectedLang", bundle.getString("language.english"));
                break;
            case "ru":
                request.setAttribute("selectedLang", bundle.getString("language.russian"));
                break;
            case "pl":
                request.setAttribute("selectedLang", bundle.getString("language.polski"));
                break;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
