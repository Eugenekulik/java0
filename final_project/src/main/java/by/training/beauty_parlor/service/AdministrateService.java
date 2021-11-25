package by.training.beauty_parlor.service;

import by.training.beauty_parlor.dao.*;
import by.training.beauty_parlor.dao.mysql.TransactionFactoryImpl;
import by.training.beauty_parlor.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class AdministrateService {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentService.class);

    public void administrateUsers(HttpServletRequest request){
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            List<User> users = userDao.findall();
            request.getSession().setAttribute("users", users);
            transaction.commit();
        } catch (DaoException e) {
            LOGGER.error("it is impossible to make transaction");
        }
    }
    public void administrateAppointments(HttpServletRequest request){
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            UserDao userDao = transaction.createDao("userDao");
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            List<Appointment> appointments = appointmentDao.findall();
            List<ProcedureEmployee> procedureEmployeeList = appointments.stream()
                            .map(appointment -> {
                                try {
                                    ProcedureEmployee temp = procedureEmployeeDao
                                            .findById(appointment.getProcedureEmployeeId());
                                    return temp;
                                } catch (DaoException e) {
                                    LOGGER.warn(String.format("an error occurred while " +
                                            "getting procedureEmployee by id: %d"
                                            ,appointment.getProcedureEmployeeId()));
                                }
                                return null;
                            }).filter(procedureEmployee -> {return procedureEmployee != null;})
                    .collect(Collectors.toList());
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
                    }).filter(procedure -> {return procedure != null;})
                    .collect(Collectors.toList());
            List<User> employees = procedureEmployeeList.stream()
                    .map(procedureEmployee -> {
                        try {
                            User employee = userDao.findById(procedureEmployee.getEmployeeId());
                            employee.setId(procedureEmployee.getId());
                            return employee;
                        } catch (DaoException e) {
                            LOGGER.warn(String.format("an error ocured " +
                                    "while getting employee by id: %d",
                                    procedureEmployee.getEmployeeId()));
                        }
                        return null;
                    }).filter(user -> {return user != null;})
                    .collect(Collectors.toList());
            List<User> clients = appointments.stream().map(appointment -> {
                try {
                    User client = userDao.findById(appointment.getUserId());
                    return client;
                } catch (DaoException e) {
                    LOGGER.warn("an error occurred " +
                            "while getting client by id: %d",
                            appointment.getUserId());
                }
                return null;
            }).filter(user -> {return user != null;}).collect(Collectors.toList());
            request.getSession().setAttribute("appointments", appointments);
            request.getSession().setAttribute("clients", clients);
            request.getSession().setAttribute("procedures", procedures);
            request.getSession().setAttribute("employees", employees);
            transaction.commit();
        } catch (DaoException e) {
            LOGGER.error("it is impossible to make transaction");
        }
    }
    public void administrateProcedures(HttpServletRequest request){
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            CategoryDao categoryDao = transaction.createDao("categoryDao");
            List<Procedure> procedures = procedureDao.findall();
            List<Category> categories = procedures.stream().map(procedure -> {
                try {
                    Category temp = categoryDao.findById(procedure.getCategoryId());
                    return temp;
                } catch (DaoException e) {
                    LOGGER.warn("an error occurred " +
                            "while getting category by id: %d"
                            ,procedure.getCategoryId());
                }
                return null;
            }).filter(category -> {return category != null;}).collect(Collectors.toList());
            request.getSession().setAttribute("categories", categories);
            request.getSession().setAttribute("procedures", procedures);
            transaction.commit();
        } catch (DaoException e) {
            LOGGER.error("it is impossible to make transaction");
        }
    }
    public void administrateGraphics(HttpServletRequest request){
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            GraphicDao graphicDao = transaction.createDao("graphicDao");
            List<Graphic> graphics = graphicDao.findall();
            request.getSession().setAttribute("users", graphics);
            transaction.commit();
        } catch (DaoException e) {
            LOGGER.error("it is impossible to make transaction");
        }
    }
}
