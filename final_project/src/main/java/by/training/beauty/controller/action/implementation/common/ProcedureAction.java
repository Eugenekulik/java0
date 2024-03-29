package by.training.beauty.controller.action.implementation.common;

import by.training.beauty.controller.action.Action;
import by.training.beauty.controller.action.PageEnum;
import by.training.beauty.domain.Category;
import by.training.beauty.domain.Score;
import by.training.beauty.domain.User;
import by.training.beauty.service.*;
import by.training.beauty.domain.Procedure;
import by.training.beauty.service.spec.ProcedureService;
import by.training.beauty.service.spec.ScoreService;
import by.training.beauty.service.spec.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implement interface Action
 * and allows get page with procedures.
 *
 * @see Action
 * @see ProcedureService
 */

public class ProcedureAction implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ProcedureAction.class);
    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public boolean isAllowed(HttpServletRequest request) {

        return request.getMethod().equals("GET");
    }


    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Integer current = null;
        try {
            current = Integer.parseInt(request.getParameter("current"));
        } catch (NumberFormatException e){
            LOGGER.info(e.getMessage());
        }
        if(current == null){
            current = 1;
        }
        try {
            ProcedureService procedureService =
                    ServiceFactory.getInstance().getProcedureService();
            ScoreService scoreService = ServiceFactory.getInstance().getScoreService();
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<Procedure> procedureList = procedureService.getProcedures();
            if(procedureList.isEmpty()) {
                return PageEnum.MAIN.getPage();
            }
            Procedure procedure = procedureService.getProcedureById(current);
            List<Category> categories = procedureService.getCategories();
            List<Score> scores = scoreService.getScoresByProcedure(procedure);
            List<User> users = new ArrayList<>();
            users.addAll(scores.stream()
                    .map(score -> userService.findById(score.getUserId()))
                    .collect(Collectors.toList()));
            request.setAttribute("categories",categories);
            request.setAttribute("procedureList", procedureList);
            request.getSession().setAttribute("procedure", procedure);
            request.setAttribute("scores", scores);
            request.getSession().setAttribute("users", users);
            page = PageEnum.PROCEDURE.getPage();
        } catch (ServiceException e) {
            LOGGER.error("it is impossible to get procedure list");
            page = PageEnum.MAIN.getPage();
        }
        return page;
    }

}
