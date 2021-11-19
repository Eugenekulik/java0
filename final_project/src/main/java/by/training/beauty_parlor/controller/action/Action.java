package by.training.beauty_parlor.controller.action;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface Action {
    boolean isRedirect();
    Set<String> getRoles();
    String execute(HttpServletRequest request);
}
