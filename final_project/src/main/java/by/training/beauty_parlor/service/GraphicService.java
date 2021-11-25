package by.training.beauty_parlor.service;

import by.training.beauty_parlor.dao.GraphicDao;
import by.training.beauty_parlor.dao.Transaction;
import by.training.beauty_parlor.dao.TransactionFactory;
import by.training.beauty_parlor.dao.mysql.TransactionFactoryImpl;
import by.training.beauty_parlor.domain.Graphic;
import by.training.beauty_parlor.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GraphicService {
    private static final Logger LOGGER = LogManager.getLogger(GraphicService.class);

    public void graphicsByEmployee(HttpServletRequest request) throws ServiceException {
        List<Graphic> graphics = new ArrayList<>();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            GraphicDao graphicDao = transaction.createDao("graphicDao");
            Integer selectedEmployee = null;
            try {
                selectedEmployee= (Integer)request.getSession()
                        .getAttribute("selectedEmployee");
            } catch (ClassCastException e){}
            graphics = graphicDao.findByEmployee(selectedEmployee);
            LocalDate date = LocalDate.parse((String) request.getSession()
                    .getAttribute("selectedDate"));
            transaction.commit();
            request.getSession().setAttribute("graphics", graphics.stream()
                    .sorted((o1, o2) -> {
                        return o1.getDate().compareTo(o2.getDate());
                    })
                    .filter(graphic -> {return graphic.getDate().toLocalDate()
                            .equals(date)?true:false;}).map(Graphic::getDate)
                    .map(LocalDateTime::toLocalTime).collect(Collectors.toList()));
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw new ServiceException(e);
        }
    }
}
