package by.training.beauty.service.implementation;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.*;
import by.training.beauty.dto.AppointmentDto;
import by.training.beauty.dto.ProcedureDto;
import by.training.beauty.dto.ScheduleDto;
import by.training.beauty.service.spec.AdministrateService;
import by.training.beauty.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This service class provides administration functionality.
 */
public class AdministrateServiceImpl implements AdministrateService {

    TransactionFactory transactionFactory;

    public AdministrateServiceImpl(TransactionFactory transactionFactory){
        this.transactionFactory = transactionFactory;
    }

    //CONSTANTS
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";
    private static final String USER_DAO = "userDao";
    private static final String PROCEDURE_DAO = "procedureDao";

    private static final Logger LOGGER
            = LogManager.getLogger(AdministrateServiceImpl.class);

    /**
     * This method allows you to get the number of pages.
     * @param tab tab with specific entities
     * @return int number of pages.
     * @throws ServiceException
     */
    @Override
    public int getPagecount(int tab) throws ServiceException {
        Transaction transaction = null;
        int pageCount = 0;
        try {
            transaction = transactionFactory.createTransaction();
            switch (tab) {
                case 1:
                    UserDao userDao = transaction.createDao(USER_DAO);
                    pageCount = (int) Math.ceil(userDao.count() / 10.0);
                    break;
                case 2:
                    AppointmentDao appointmentDao
                            = transaction.createDao("appointmentDao");
                    pageCount = (int) Math.ceil(appointmentDao.count() / 10.0);
                    break;
                case 3:
                    ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
                    pageCount = (int) Math.ceil(procedureDao.count() / 10.0);
                    break;
                case 4:
                    ScheduleDao scheduleDao = transaction.createDao("scheduleDao");
                    pageCount = (int) Math.ceil(scheduleDao.count() / 10.0);
                    break;
                default:
                    break;
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException(e);
        }
        return pageCount;
    }

    /**
     * This method return entities required for user administration.
     * @param paginationPage page number
     * @return List of users
     * @throws ServiceException
     */
    @Override
    public List<User> administrateUsers(int paginationPage) throws ServiceException {
        Transaction transaction = null;
        List<User> users = null;
        try {
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            users = userDao.findInterval((paginationPage - 1) * 10, 10);
            for (User user : users) {
                roleDao.findByUser(user.getId()).stream().forEach(user::addRole);
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException();
        }
        return users;
    }

    /**
     * This method return entities required for appointment administration.
     * @param paginationPage page number
     * @return List of appointments
     * @throws ServiceException
     */
    @Override
    public List<AppointmentDto> administrateAppointments(int paginationPage)
            throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao(DaoEnum.CATEGORY.getDao());
            AppointmentDao appointmentDao
                    = transaction.createDao(DaoEnum.APPOINTMENT.getDao());
            UserDao userDao = transaction.createDao(USER_DAO);
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            List<Appointment> appointments = appointmentDao
                    .findInterval((paginationPage - 1) * 10, 10);
            for (Appointment appointment: appointments) {
                appointment.setProcedure(procedureDao.findByAppointment(appointment.getId()));
                appointment.setEmployee(userDao.findEmployeeByAppointment(appointment.getId()));
            }
            List<AppointmentDto> appointmentDtoList = new ArrayList<>();
            appointments.stream().forEach(appointment -> {
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.setId(appointment.getId());
                appointmentDto.setStatus(appointment.getStatus());
                appointmentDto.setDate(appointment.getDate());
                appointmentDto.setPrice(appointment.getPrice());
                appointmentDto.setEmployee(appointment.getEmployee().getName());
                appointmentDto.setProcedure(appointment.getProcedure().getName());
                appointmentDto.setClient("" + appointment.getUserId());
                appointmentDtoList.add(appointmentDto);
            });
            transaction.commit();
            return appointmentDtoList;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException();
        }
    }

    /**
     * This method return entities required for procedure administration.
     * @param paginationPage page number
     * @return List of procedures
     * @throws ServiceException
     */
    @Override
    public List<ProcedureDto> administrateProcedures(int paginationPage)
            throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            CategoryDao categoryDao = transaction.createDao("categoryDao");
            List<Procedure> procedures = procedureDao
                    .findInterval((paginationPage - 1) * 10, 10);
            Set<Category> categories = procedures.stream().map(procedure -> {
                try {
                    return categoryDao.findById(procedure.getCategoryId());
                } catch (DaoException e) {
                    LOGGER.warn("an error occurred " +
                                    "while getting category by id: {}"
                            , procedure.getCategoryId());
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toSet());
            List<ProcedureDto> procedureDtoList = new ArrayList<>();
            procedures.stream().forEach(procedure -> {
                ProcedureDto procedureDto = new ProcedureDto();
                procedureDto.setId(procedure.getId());
                procedureDto.setDescription(procedure.getDescription());
                procedureDto.setName(procedure.getName());
                procedureDto.setElapsedTime(procedure.getElapsedTime());
                procedureDto.setCategory(categories.stream()
                        .filter(category -> category.getId() == procedure.getCategoryId())
                        .findFirst().get().getName());
                procedureDtoList.add(procedureDto);
            });
            transaction.commit();
            return procedureDtoList;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException();
        }
    }

    /**
     * This method return entities required for schedule administration.
     * @param paginationPage page number
     * @return List of schedules
     */
    @Override
    public List<ScheduleDto> administrateSchedules(int paginationPage)
            throws ServiceException {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao("scheduleDao");
            UserDao userDao = transaction.createDao(USER_DAO);

            List<Schedule> schedules = scheduleDao
                    .findInterval((paginationPage - 1) * 10, 10);
            List<User> employees = userDao.findEmployees();
            List<ScheduleDto> scheduleDtoList = new ArrayList<>();

            schedules.stream().forEach(schedule -> {
                ScheduleDto scheduleDto = new ScheduleDto();
                scheduleDto.setId(schedule.getId());
                scheduleDto.setDate(schedule.getDate());
                scheduleDto.setAppointmentId(schedule.getAppointmentId());
                scheduleDto.setEmployee(employees.stream()
                        .filter(employee -> employee.getId() == schedule.getEmployeeId())
                        .findAny().get().getName());
                scheduleDtoList.add(scheduleDto);
            });
            transaction.commit();
            return scheduleDtoList;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error(ROLLBACK_ERROR);
            }
            throw new ServiceException();
        }
    }
}
