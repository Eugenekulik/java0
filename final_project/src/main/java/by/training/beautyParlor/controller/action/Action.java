package by.training.beautyParlor.controller.action;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface Action {
    boolean isRedirect();
    Set<String> getRoles();
    String execute(HttpServletRequest request);
    String getMethod();
}
