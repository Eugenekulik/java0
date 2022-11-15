package by.training.beauty.service;

import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.*;
import by.training.beauty.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This service allows to do some activities with appointments.
 */
public class AppointmentService {
    //CONSTANTS
    private static final String APPOINTMENT_DAO = "appointmentDao";
    private static final String PROCEDURE_EMPLOYEE_DAO
            = "procedureEmployeeDao";
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER
            = LogManager.getLogger(AppointmentService.class);

    /**
     * This method allows you to delete appointment from the store.
     * @param id identifier of appointment.
     * @throws ServiceException
     */
    public void deleteAppointment(int id) throws ServiceException {
        TransactionFactory transactionFactory;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao
                    = transaction.createDao(APPOINTMENT_DAO);
            appointmentDao.delete(id);
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR,e1);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to get appointment by specific user.
     * @param user
     * @return List of user's appointments.
     * @throws ServiceException
     */
    public List<Entity> usersAppointment(User user) throws ServiceException {
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transaction = factory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(APPOINTMENT_DAO);
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao(PROCEDURE_EMPLOYEE_DAO);
            UserDao userDao = transaction.createDao("userDao");
            if (user != null) {
                List<Appointment> appointments
                        = appointmentDao.getUsersAppointment(user);
                Set<ProcedureEmployee> procedureEmployeeList =
                    appointments.stream().map(appointment -> {
                        try {
                            return procedureEmployeeDao
                                    .findById(appointment
                                            .getProcedureEmployeeId());
                        } catch (DaoException e) {
                            LOGGER.warn("an error occured while getting " +
                                    "procedureAppointment by id: {}",
                                    appointment.getProcedureEmployeeId());
                        }
                        return null;
                    }).collect(Collectors.toSet());
                Set<Procedure> procedures = procedureEmployeeList.stream()
                        .map(procedureEmployee -> {
                            try {
                                Procedure procedure = procedureDao
                                        .findById(procedureEmployee
                                                .getProcedureId());
                                procedure.setId(procedureEmployee.getId());
                                return procedure;
                            } catch (DaoException e) {
                                LOGGER.warn("an error occured " +
                                        "while getting procedure by id: {}",
                                        procedureEmployee.getProcedureId());
                            }
                            return null;
                        }).collect(Collectors.toSet());
                Set<User> emloyees = procedureEmployeeList.stream()
                        .map(procedureEmployee -> {
                            try {
                                User employee = userDao
                                        .findById(procedureEmployee
                                                .getEmployeeId());
                                employee.setId(procedureEmployee.getId());
                                return employee;
                            } catch (DaoException e) {
                                LOGGER.warn("an error ocured " +
                                        "while getting employee by id: {}",
                                        procedureEmployee.getEmployeeId());
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
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR,e1);
            }
            throw new ServiceException(e);
        }
        return entities;
    }

    /**
     * This class allows you to add appointment to the store.
     * @param appointment
     * @param procedure type of procedure
     * @param employeeId identifier of the employee
     * @return
     * @throws ServiceException
     */
    public boolean addAppointment(Appointment appointment
            ,Procedure procedure,int employeeId) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao(PROCEDURE_EMPLOYEE_DAO);
            ProcedureEmployee procedureEmployee = procedureEmployeeDao
                    .findByProcedureEmployee(procedure,
                    userDao.findById(employeeId));
            appointment.setProcedureEmployeeId(procedureEmployee.getId());
            appointment.setPrice(procedureEmployee.getPrice());
            appointment.setStatus(1);
            AppointmentDao appointmentDao
                    = transaction.createDao(APPOINTMENT_DAO);
            int isSuccess = appointmentDao.create(appointment);
            if (isSuccess != 0) {
                User employee = userDao.findById(employeeId);
                ScheduleDao scheduleDao = transaction.createDao("scheduleDao");
                Schedule schedule = scheduleDao
                        .findByEmployeeDate(appointment.getDate(), employee);
                schedule.setAppointmentId(isSuccess);
                scheduleDao.update(schedule);
            }
            transaction.commit();
            return true;
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR,e1);
            }
            throw new ServiceException();
        }
    }

    /**
     * This method allows you to update information about appointment.
     * @param appointment
     * @throws ServiceException
     */
    public void updateAppointment(Appointment appointment)
            throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao
                    = transaction.createDao(APPOINTMENT_DAO);
            appointmentDao.update(appointment);
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR,e1);
            }
            throw new ServiceException(e);
        }
    }
}
