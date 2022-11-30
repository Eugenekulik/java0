package by.training.beauty.controller.action.implementation.client;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.Score;
import by.training.beauty.domain.User;
import by.training.beauty.service.spec.ScoreService;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class ScoreAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ScoreAction.class);
    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        return  roles != null
                && roles.contains(new Role("client"))
                && request.getMethod().equals("POST");
    }


    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getParameter("method");
        switch (method){
            case "create":
                create(request);
                break;
            case "update":
                break;
            case "delete":
                delete(request);
                break;
            default:
                LOGGER.warn(()->"Unsupported operation with method " + method);
        }
        return "/procedure.html";
    }

    private boolean create(HttpServletRequest request){
        Score score = new Score();
        score.setDate(LocalDateTime.now());
        User user = (User) request.getSession().getAttribute("user");
        score.setUserId((user.getId()));
        score.setComment(request.getParameter("comment"));
        Integer value  = 1;
        try {
            value = Integer.parseInt(request.getParameter("score"));
        } catch (NumberFormatException e){
            LOGGER.warn("invalid score number");
        }
        score.setValue(value.byteValue());
        Integer appointmentId = null;
        try  {
            appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        } catch (NumberFormatException e) {
            LOGGER.warn("invalid appointment id");
        }
        score.setAppointmentId(appointmentId);
        ScoreService scoreService = ServiceFactory.getInstance().getScoreService();
        return scoreService.addScore(score)!=null;
    }

    private boolean delete(HttpServletRequest request){
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            LOGGER.warn("Error occurred while trying to delete score: invalid id");
        }
        return ServiceFactory.getInstance().getScoreService().deleteScore(id);
    }


}
