package by.training.beauty_parlor.controller;

import by.training.beauty_parlor.controller.action.ActionFactory;
import by.training.beauty_parlor.controller.action.Action;
import by.training.beauty_parlor.controller.action.PageEnum;
import by.training.beauty_parlor.dao.pool.ConnectionPool;
import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.service.ConnectionPoolInitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

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
            page = PageEnum.MAIN.getPage();
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
                resp.sendRedirect("/");
            } catch (IOException ioException) {
                LOGGER.error("It is impossible to forward", e);
            }
            LOGGER.error("It is impossible to forward", e);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPoolInitService connectionPoolInitService = new ConnectionPoolInitService();
        connectionPoolInitService.init();
    }
}
