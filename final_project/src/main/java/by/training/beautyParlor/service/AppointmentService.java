package by.training.beautyParlor.service;

import by.training.beautyParlor.dao.*;
import by.training.beautyParlor.dao.mysql.TransactionFactoryImpl;
import by.training.beautyParlor.domain.*;
import by.training.beautyParlor.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppointmentService {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentService.class);

    public void deleteAppointment(int id) throws ServiceException {
        TransactionFactory transactionFactory;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            Appointment tempAppointment = appointmentDao
                    .findById(id);
            GraphicDao graphicDao = transaction.createDao("graphicDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            Graphic graphic = new Graphic();
            graphic.setEmployeeId(procedureEmployeeDao.findById(tempAppointment
                    .getProcedureEmployeeId()).getEmployeeId());
            graphic.setDate(tempAppointment.getDate());
            graphicDao.create(graphic);
            appointmentDao.delete(id);
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction",e1);
            }
            throw new ServiceException(e);
        }
    }

    public List<Entity> usersAppointment(User user) throws ServiceException {
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transaction = factory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            UserDao userDao = transaction.createDao("userDao");
            if (user != null) {
                List<Appointment> appointments = appointmentDao.getUsersAppointment(user);
                Set<ProcedureEmployee> procedureEmployeeList =
                        appointments.stream().map(appointment -> {
                            try {
                                return procedureEmployeeDao.findById(appointment.getProcedureEmployeeId());
                            } catch (DaoException e) {
                                LOGGER.warn(String.format("an error occured while getting " +
                                        "procedureAppointment by id: %d", appointment.getProcedureEmployeeId()));
                            }
                            return null;
                        }).collect(Collectors.toSet());
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
                        }).collect(Collectors.toSet());
                Set<User> emloyees = procedureEmployeeList.stream()
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
                        }).collect(Collectors.toSet());
                entities = new ArrayList<>();
                entities.addAll(appointments);
                entities.addAll(emloyees);
                entities.addAll(procedures);
            }
            transaction.commit();

        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction",e1);
            }
            throw new ServiceException(e);
        }
        return entities;
    }

    public boolean addAppointment(Appointment appointment
            ,Procedure procedure,int employeeId) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            ProcedureEmployeeDao procedureEmployeeDao = transaction.createDao("procedureEmployeeDao");
            ProcedureEmployee procedureEmployee = procedureEmployeeDao.findByProcedureEmployee(
                    procedure,
                    userDao.findById(employeeId));
            appointment.setProcedureEmployeeId(procedureEmployee.getId());
            appointment.setPrice(procedureEmployee.getPrice());
            appointment.setStatus(1);
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            boolean isSuccess = appointmentDao.create(appointment);
            if (isSuccess) {
                GraphicDao graphicDao = transaction.createDao("graphicDao");
                Graphic graphic = graphicDao.findByDate(appointment.getDate());
                graphicDao.delete(graphic.getId());
            }

            transaction.commit();
            return isSuccess;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction",e1);
            }
            throw new ServiceException();
        }
    }

    public void updateAppointment(Appointment appointment) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            appointmentDao.update(appointment);

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
