package by.training.beauty.controller.action.implementation.client;

import by.training.beauty.controller.action.Action;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.Score;
import by.training.beauty.domain.User;
import by.training.beauty.service.ScoreService;
import by.training.beauty.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class AddScoreAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(AddScoreAction.class);
    @Override
    public boolean isRedirect() {
        return true;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {
        List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
        if(roles != null
                && roles.contains(new Role("client"))
                && request.getMethod().equals("POST")) return true;
        else return false;
    }


    @Override
    public String execute(HttpServletRequest request) {
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
        scoreService.addScore(score);
        return "/procedure.html";
    }


}
