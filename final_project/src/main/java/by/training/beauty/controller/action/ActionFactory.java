package by.training.beauty.controller.action;

import by.training.beauty.controller.action.implementation.common.EmptyAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * This class allows init action by the request.
 */

public class ActionFactory {
    private static final Logger logger = LogManager.getLogger(ActionFactory.class);
    private HttpServletRequest request;
    private static final String UNKNOWN = "unknown";

    public ActionFactory(HttpServletRequest request) {
        this.request = request;
    }

    public Action initCommand() {
        Action action = null;
        String commandType = (String) request.getAttribute("action");
        if (commandType == null) {
            return new EmptyAction();
        }
        commandType = commandType.substring(1).toUpperCase(Locale.ROOT);
        try {
            ActionEnum actionEnum = ActionEnum.valueOf(commandType);
            action = actionEnum.getAction().newInstance();
        } catch (InstantiationException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        if(action == null){
            return new EmptyAction();
        }
        return action;
    }
}
