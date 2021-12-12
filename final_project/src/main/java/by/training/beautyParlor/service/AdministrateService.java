package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.*;
import by.training.beautyParlor.dao.mysql.TransactionFactoryImpl;
import by.training.beautyParlor.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdministrateService {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentService.class);

    public int getPagecount(int tab) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        int pageCount = 0;
        try{
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
        switch (tab){
            case 1:
                UserDao userDao = transaction.createDao("userDao");
                pageCount = (int)Math.ceil(userDao.count()/10.0);
                break;
            case 2:
                AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
                pageCount = (int)Math.ceil(appointmentDao.count()/10.0);
                break;
            case 3:
                ProcedureDao procedureDao = transaction.createDao("procedureDao");
                pageCount = (int)Math.ceil(procedureDao.count()/10.0);
                break;
            case 4:
                GraphicDao graphicDao = transaction.createDao("graphicDao");
                pageCount = (int)Math.ceil(graphicDao.count()/10.0);
                break;
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
        return pageCount;
    }

    public List<User> administrateUsers(int paginationPage) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<User> users = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            users = userDao.findInterval((paginationPage - 1)*10, 10);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw  new ServiceException();
        }
        return users;
    }
    public List<Entity> administrateAppointments(int paginationPage) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            UserDao userDao = transaction.createDao("userDao");
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            List<Appointment> appointments = appointmentDao.findInterval((paginationPage - 1)*10,10);
            Set<ProcedureEmployee> procedureEmployeeList = appointments.stream()
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
                    .collect(Collectors.toSet());
            Set<Procedure> procedures = procedureEmployeeList.stream()
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
                    .collect(Collectors.toSet());
            Set<User> employees = procedureEmployeeList.stream()
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
                    .collect(Collectors.toSet());
            Set<User> clients = appointments.stream().map(appointment -> {
                try {
                    User client = userDao.findById(appointment.getUserId());
                    return client;
                } catch (DaoException e) {
                    LOGGER.warn("an error occurred " +
                            "while getting client by id: %d",
                            appointment.getUserId());
                }
                return null;
            }).filter(user -> {return user != null;}).collect(Collectors.toSet());
            entities = new ArrayList<>();
            entities.addAll(appointments);
            entities.addAll(clients);
            entities.addAll(procedures);
            entities.addAll(employees);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw  new ServiceException();
        }
        return entities;
    }
    public List<Entity> administrateProcedures(int paginationPage) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            CategoryDao categoryDao = transaction.createDao("categoryDao");
            List<Procedure> procedures = procedureDao.findInterval((paginationPage - 1)*10, 10);
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
            entities = new ArrayList<>();
            entities.addAll(procedures);
            entities.addAll(categories);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw  new ServiceException();
        }
        return entities;
    }
    public List<Entity> administrateGraphics(int paginationPage) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            GraphicDao graphicDao = transaction.createDao("graphicDao");
            UserDao userDao = transaction.createDao("userDao");

            List<Graphic> graphics = graphicDao.findInterval((paginationPage - 1)*10, 10);
            List<User> employees = userDao.findEmployees();
            entities = new ArrayList<>();
            entities.addAll(employees);
            entities.addAll(graphics);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction");
            }
            throw  new ServiceException();
        }
        return entities;
    }
}
