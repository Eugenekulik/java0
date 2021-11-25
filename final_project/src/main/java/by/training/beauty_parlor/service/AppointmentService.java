package by.training.beauty_parlor.service;

import by.training.beauty_parlor.dao.*;
import by.training.beauty_parlor.dao.mysql.TransactionFactoryImpl;
import by.training.beauty_parlor.domain.*;
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

public class AppointmentService {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentService.class);

    public void usersAppointment(HttpServletRequest request) throws ServiceException {
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            transaction = factory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            if(request.getParameter("delete") != null) {
                Appointment tempAppointment = appointmentDao
                        .findById(Integer.parseInt(request.getParameter("delete")));
                GraphicDao graphicDao = transaction.createDao("graphicDao");
                ProcedureEmployeeDao procedureEmployeeDao =
                        transaction.createDao("procedureEmployeeDao");
                Graphic graphic = new Graphic();
                graphic.setEmployeeId(procedureEmployeeDao.findById(tempAppointment
                        .getProcedureEmployeeId()).getEmployeeId());
                graphic.setDate(tempAppointment.getDate());
                graphicDao.create(graphic);
                appointmentDao.delete(Integer.parseInt(request.getParameter("delete")));
            }
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            UserDao userDao = transaction.createDao("userDao");
            String userName = (String) request.getSession().getAttribute("user");
            User user = userDao.findByName(userName);
            if(user != null) {
                appointments = appointmentDao.getUsersAppointment(user);
                List<ProcedureEmployee> procedureEmployeeList =
                        appointments.stream().map(appointment -> {
                            try {
                                return procedureEmployeeDao.findById(appointment.getProcedureEmployeeId());
                            } catch (DaoException e) {
                                LOGGER.warn(String.format("an error occured while getting " +
                                        "procedureAppointment by id: %d", appointment.getProcedureEmployeeId()));
                            }
                            return null;
                        }).collect(Collectors.toList());
                List<Procedure> procedures = procedureEmployeeList.stream()
                        .map(procedureEmployee -> {
                            try {
                                Procedure procedure = procedureDao.findById(procedureEmployee.getProcedureId());
                                procedure.setId(procedureEmployee.getId());
                                return procedure;
                            } catch (DaoException e) {
                                LOGGER.warn(String.format("an error occured " +
                                        "while getting procedure by id: %d", procedureEmployee.getProcedureId()));
                            }
                            return null;
                        }).collect(Collectors.toList());
                List<User> emloyees = procedureEmployeeList.stream()
                        .map(procedureEmployee -> {
                            try {
                                User employee = userDao.findById(procedureEmployee.getEmployeeId());
                                employee.setId(procedureEmployee.getId());
                                return employee;
                            } catch (DaoException e) {
                                LOGGER.warn(String.format("an error ocured " +
                                        "while getting employee by id: %d", procedureEmployee.getEmployeeId()));
                            }
                            return null;
                        }).collect(Collectors.toList());
                request.getSession().setAttribute("appointments", appointments);
                request.getSession().setAttribute("employees", emloyees);
                request.getSession().setAttribute("procedures", procedures);
            }
            transaction.commit();
            request.getSession().setAttribute("appointments", appointments);
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        }
    }
    public boolean addAppointment(HttpServletRequest request) throws ServiceException {
        Appointment appointment = new Appointment();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            appointment.setUserId(userDao
                    .findByName((String) request.getSession().getAttribute("user")).getId());
            ProcedureEmployeeDao procedureEmployeeDao = transaction.createDao("procedureEmployeeDao");
            ProcedureEmployee procedureEmployee = procedureEmployeeDao.findByProcedureEmployee(
                    (Procedure)request.getSession().getAttribute("procedure"),
                    userDao.findById((Integer)request.getSession()
                            .getAttribute("selectedEmployee")));
            appointment.setProcedureEmployeeId(procedureEmployee.getId());
            appointment.setPrice(procedureEmployee.getPrice());
            appointment.setStatus(1);
            LocalDate localDate = LocalDate.parse((String)request.getSession().getAttribute("selectedDate"));
            LocalTime localTime = LocalTime.parse(request.getParameter("timeSelect"));
            appointment.setDate(LocalDateTime.of(localDate,localTime));
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            boolean isSuccess = appointmentDao.create(appointment);
            if(isSuccess) {
                GraphicDao graphicDao = transaction.createDao("graphicDao");
                Graphic graphic = graphicDao.findByDate(LocalDateTime.of(localDate,localTime));
                graphicDao.delete(graphic.getId());
            }
            request.getSession().removeAttribute("selectedDate");
            request.getSession().removeAttribute("selectedEmployee");
            request.getSession().removeAttribute("procedure");
            transaction.commit();
            return isSuccess;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw new ServiceException();
        }
    }
}
