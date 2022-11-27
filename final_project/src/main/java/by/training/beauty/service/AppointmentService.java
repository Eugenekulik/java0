package by.training.beauty.service;

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
    private TransactionFactory transactionFactory;

    public AppointmentService(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    /**
     * This method allows you to delete appointment from the store.
     * @param id identifier of appointment.
     * @throws ServiceException
     */
    public void cancelAppointment(int id) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao
                    = transaction.createDao(APPOINTMENT_DAO);
            appointmentDao.cancel(id);
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
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(APPOINTMENT_DAO);
            ProcedureDao procedureDao = transaction.createDao("procedureDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao(PROCEDURE_EMPLOYEE_DAO);
            UserDao userDao = transaction.createDao("userDao");
            if (user != null) {
                List<Appointment> appointments
                        = appointmentDao.getUserAppointments(user.getId());
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
     * @param procedureId id of procedure
     * @param employeeId identifier of the employee
     * @return
     * @throws ServiceException
     */
    public boolean addAppointment(Appointment appointment
            ,int procedureId,int employeeId) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao(PROCEDURE_EMPLOYEE_DAO);
            ProcedureEmployee procedureEmployee = procedureEmployeeDao
                    .findByProcedureEmployee(procedureId, employeeId);
            appointment.setProcedureEmployeeId(procedureEmployee.getId());
            appointment.setPrice(procedureEmployee.getPrice());
            appointment.setStatus(1);
            AppointmentDao appointmentDao
                    = transaction.createDao(APPOINTMENT_DAO);
            Appointment result = appointmentDao.create(appointment);
            if (result != null) {
                User employee = userDao.findById(employeeId);
                ScheduleDao scheduleDao = transaction.createDao("scheduleDao");
                Schedule schedule = scheduleDao
                        .findByEmployeeDate(appointment.getDate(), employee);
                schedule.setAppointmentId(result.getId());
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
        Transaction transaction = null;
        try {
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

    public void archive() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao
                    = transaction.createDao(APPOINTMENT_DAO);
            appointmentDao.archive();
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR,e1);
            }
        }
    }
}
