package by.training.beauty.service.implementation;

import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.*;
import by.training.beauty.dao.DaoException;
import by.training.beauty.dto.AppointmentDto;
import by.training.beauty.service.spec.AppointmentService;
import by.training.beauty.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This service allows to do some activities with appointments.
 */
public class AppointmentServiceImpl implements AppointmentService {
    //CONSTANTS
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER
            = LogManager.getLogger(AppointmentService.class);
    private TransactionFactory transactionFactory;

    public AppointmentServiceImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    /**
     * This method allows you to delete appointment from the store.
     * @param id identifier of appointment.
     * @throws ServiceException
     */
    @Override public void cancelAppointment(int id) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao
                    = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
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
    @Override public List<Appointment> usersAppointment(User user) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            ProcedureDao procedureDao = transaction.createDao(DaoEnum.PROCEDURE.getDao());
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            if (user != null) {
                List<Appointment> appointments
                        = appointmentDao.getUserAppointments(user.getId());
                for (Appointment appointment:appointments) {
                    appointment.setEmployee(userDao.findEmployeeByAppointment(appointment.getId()));
                    appointment.setProcedure(procedureDao.findByAppointment(appointment.getId()));
                }
                return appointments;
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
        return Collections.EMPTY_LIST;
    }

    /**
     * This class allows you to add appointment to the store.
     * @param appointment
     * @param procedureId id of procedure
     * @param employeeId identifier of the employee
     * @return
     * @throws ServiceException
     */
    @Override public boolean addAppointment(Appointment appointment
            ,int procedureId,int employeeId) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao("userDao");
            appointment.setEmployee(new User.Builder().setId(employeeId).build());
            appointment.setProcedure(new Procedure.Builder().setId(procedureId).build());
            appointment.setStatus(1);
            AppointmentDao appointmentDao
                    = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            Appointment result = appointmentDao.create(appointment);
            if (result != null) {
                User employee = userDao.findById(employeeId);
                ScheduleDao scheduleDao = transaction.createDao("scheduleDao");
                Schedule schedule = scheduleDao
                        .findByEmployeeDate(appointment.getDate(), employee.getId());
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
    @Override public void updateAppointment(Appointment appointment)
            throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao
                    = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
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

    @Override public void archive() {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            AppointmentDao appointmentDao
                    = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
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
