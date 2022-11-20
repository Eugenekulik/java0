package by.training.beauty.controller.filter;

import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class implement Filter and  allows you to keep track of
 * the internationalization of the application
 */

public class LanguageFilter implements Filter {
    private static final String LANG = "language";
    private static final String SELECT_LANG = "selectedLang";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse responce = (HttpServletResponse) servletResponse;
        String select = request.getParameter(LANG);
        select = StringEscapeUtils.unescapeHtml(select);
        Cookie lang = null;
        if (select == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : request.getCookies()) {
                    if (c.getName().equals(LANG)) {
                        lang = c;
                    }
                }
            }
            if (lang == null) {
                lang = new Cookie(LANG, "en_US");
            }

        } else {
            lang = new Cookie(LANG, select);
        }
        String[] localeArray = lang.getValue().split("_");
        Locale locale = new Locale(localeArray[0], localeArray[1]);
        ResourceBundle bundle = ResourceBundle.getBundle("text", locale);
        responce.addCookie(lang);
        request.getSession().setAttribute("language",lang.getValue());
        request.setAttribute("text", bundle);
        switch (localeArray[0]) {
            case "en":
                request.setAttribute(SELECT_LANG, bundle.getString("language.english"));
                break;
            case "ru":
                request.setAttribute(SELECT_LANG, bundle.getString("language.russian"));
                break;
            case "pl":
                request.setAttribute(SELECT_LANG, bundle.getString("language.polski"));
                break;
            default:
                request.setAttribute(SELECT_LANG, bundle.getString("language.english"));
                break;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
