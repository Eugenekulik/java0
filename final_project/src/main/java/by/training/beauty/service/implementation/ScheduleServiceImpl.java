package by.training.beauty.service.implementation;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.*;
import by.training.beauty.service.spec.ScheduleService;
import by.training.beauty.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This service allows to do some activities with schedules.
 */
public class ScheduleServiceImpl implements ScheduleService {
    //CONSTANTS
    private static final String SCHEDULE_DAO = "scheduleDao";
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";

    private static final Logger LOGGER
            = LogManager.getLogger(ScheduleService.class);
    private TransactionFactory transactionFactory;

    public ScheduleServiceImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    /**
     * This method allows you to get all free time in the schedule on a specific date
     * and for a specific employee.
     * @param selectedEmployee
     * @param date
     * @return
     * @throws ServiceException
     */
    @Override public List<LocalTime> schedulesByEmployeeDate(int selectedEmployee, LocalDate date)
            throws ServiceException {
        List<Schedule> schedules;
        Transaction transaction = null;
        try {
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
     * @return
     */
    @Override public boolean addSchedule(int employeeId, LocalDate date)
            throws ServiceException {
        Transaction transaction = null;
        try {
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
            return true;
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
     * @return
     */
    @Override public boolean deleteSchedule(int id) throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(SCHEDULE_DAO);
            boolean result = scheduleDao.delete(id);
            transaction.commit();
            return result;
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


    @Override public boolean archive() {
        Transaction transaction = null;
        try{
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao(DaoEnum.SCHEDULE.getDao());
            scheduleDao.archive();
            transaction.commit();
            return true;
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return false;
    }
}
