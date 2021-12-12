package by.training.beautyParlor.controller;

import by.training.beautyParlor.controller.action.ActionFactory;
import by.training.beautyParlor.controller.action.Action;
import by.training.beautyParlor.controller.action.PageEnum;
import by.training.beautyParlor.service.ConnectionPoolService;
import by.training.beautyParlor.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class extends HttpServlet and realize
 * general control of application.
 * Controller allows request handling.
 */

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        String page;
        ActionFactory actionFactory = new ActionFactory(req);
        Action action = actionFactory.initCommand();
        if (action != null) {
            page = action.execute(req);
        } else {
            page = PageEnum.ERROR.getPage();
        }

        try {
            if (page != null) {
                if (action.isRedirect()) {
                    resp.sendRedirect(req.getContextPath() + page);
                } else {
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
                    requestDispatcher.forward(req, resp);
                }
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/");
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            try {
                resp.sendRedirect("/main.html");
            } catch (IOException ioException) {
                LOGGER.error("It is impossible to forward", e);
            }
            LOGGER.error("It is impossible to forward", e);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPoolService connectionPoolService =
                ServiceFactory.getInstance().getConnectionPoolService();
        connectionPoolService.init();
    }

    @Override
    public void destroy() {
        ConnectionPoolService connectionPoolService =
                ServiceFactory.getInstance().getConnectionPoolService();
        connectionPoolService.destroy();
    }
}
