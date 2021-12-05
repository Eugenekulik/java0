package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.*;
import by.training.beautyParlor.dao.mysql.TransactionFactoryImpl;
import by.training.beautyParlor.domain.Graphic;
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

    public List<LocalTime> graphicsByEmployee(int selectedEmployee, LocalDate date) throws ServiceException {
        List<Graphic> graphics = new ArrayList<>();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            GraphicDao graphicDao = transaction.createDao("graphicDao");
            graphics = graphicDao.findByEmployee(selectedEmployee);
            transaction.commit();
            return graphics.stream()
                    .sorted((o1, o2) -> {
                        return o1.getDate().compareTo(o2.getDate());
                    })
                    .filter(graphic -> {return graphic.getDate().toLocalDate()
                            .equals(date)?true:false;}).map(Graphic::getDate)
                    .map(LocalDateTime::toLocalTime).collect(Collectors.toList());
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw new ServiceException(e);
        }
    }
    public void addGraphic(int employeeId, LocalDate date) throws ServiceException {
        TransactionFactory transactionFactory;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            GraphicDao graphicDao = transaction.createDao("graphicDao");
            List<LocalTime> times = List.of(LocalTime.parse("09:00:00"),LocalTime.parse("10:00:00"),
                    LocalTime.parse("11:00:00"),LocalTime.parse("12:00:00"),LocalTime.parse("13:00:00"),
                    LocalTime.parse("14:00:00"),LocalTime.parse("15:00:00"),LocalTime.parse("16:00:00"),
                    LocalTime.parse("17:00:00"));
            Graphic graphic = new Graphic();
            graphic.setEmployeeId(employeeId);
            for (LocalTime time:times) {
                LocalDateTime dateTime = LocalDateTime.of(date,time);
                graphic.setDate(dateTime);
                graphicDao.create(graphic);
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw new ServiceException(e);
        }
    }
    public void deleteGraphic(Integer id) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            GraphicDao graphicDao = transaction.createDao("graphicDao");
            UserDao userDao = transaction.createDao("userDao");
            if (id != null) {
                graphicDao.delete(id);
            } else {
                LOGGER.warn(String.format("an error occurred while delete graphic by id: %d", id));
            }
            transaction.commit();
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
