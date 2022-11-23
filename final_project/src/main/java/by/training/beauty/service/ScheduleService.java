package by.training.beauty.service;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This service allows to do some activities with schedules.
 */
public class ScheduleService {
    //CONSTANTS
    private static final String SCHEDULE_DAO = "scheduleDao";
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER
            = LogManager.getLogger(ScheduleService.class);

    /**
     * This method allows you to get all free time in the schedule on a specific date
     * and for a specific employee.
     * @param selectedEmployee
     * @param date
     * @return
     * @throws ServiceException
     */
    public List<LocalTime> schedulesByEmployeeDate(int selectedEmployee,
                                                   LocalDate date)
            throws ServiceException {
        List<Schedule> schedules;
        TransactionFactory transactionFactory;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(SCHEDULE_DAO);
            schedules = scheduleDao.findByEmployee(selectedEmployee);
            transaction.commit();
            return schedules.stream()
                    .sorted(Comparator.comparing(Schedule::getDate))
                    .filter(schedule -> schedule.getDate().toLocalDate()
                    .equals(date)).map(Schedule::getDate)
                    .map(LocalDateTime::toLocalTime)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to add new schedule to the store.
     * @param employeeId
     * @param date
     * @throws ServiceException
     */
    public void addSchedule(int employeeId, LocalDate date)
            throws ServiceException {
        TransactionFactory transactionFactory;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(SCHEDULE_DAO);
            List<LocalTime> times = List.of(LocalTime.parse("09:00:00"),
                    LocalTime.parse("10:00:00"),LocalTime.parse("11:00:00"),
                    LocalTime.parse("12:00:00"),LocalTime.parse("13:00:00"),
                    LocalTime.parse("14:00:00"),LocalTime.parse("15:00:00"),
                    LocalTime.parse("16:00:00"),LocalTime.parse("17:00:00"));
            Schedule schedule = new Schedule();
            schedule.setEmployeeId(employeeId);
            for (LocalTime time:times) {
                LocalDateTime dateTime = LocalDateTime.of(date,time);
                schedule.setDate(dateTime);
                scheduleDao.create(schedule);
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to delete schedule from the store by id.
     * @param id identifier of the schedule
     * @throws ServiceException
     */
    public void deleteschedule(Integer id) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(SCHEDULE_DAO);
            if (id != null) {
                scheduleDao.delete(id);
            } else {
                LOGGER.warn("an error occurred while " +
                        "delete schedule by id: {}", id);
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * This method allows you to get all schedules for a specific employee.
     * @param employee
     * @return
     * @throws ServiceException
     */
    public List<Entity> getSchedulesByEmployee(User employee)
            throws ServiceException {
        List<Entity> schedules = new ArrayList<>();
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(SCHEDULE_DAO);
            AppointmentDao appointmentDao
                    = transaction.createDao("appointmentDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            List<ProcedureEmployee> procedureEmployeeList
                    = procedureEmployeeDao.findByEmployee(employee);
            List<Appointment> appointments = new ArrayList<>();
            for (ProcedureEmployee temp:procedureEmployeeList) {
                appointments.addAll(appointmentDao.getEmployeeAppointment(temp));
            }
            schedules.addAll(scheduleDao.findByEmployee(employee.getId()));
            schedules.addAll(appointments);
            transaction.commit();
            return schedules;
        } catch (DaoException e) {
            try {
                if(transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
    }

    public void archive() {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try{
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            scheduleDao.archive();
            transaction.commit();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
