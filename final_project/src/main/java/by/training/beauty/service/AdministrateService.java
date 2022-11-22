package by.training.beauty.service;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.*;
import by.training.beauty.dto.AppointmentDto;
import by.training.beauty.dto.ProcedureDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.PropertySource;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This service class provides administration functionality.
 */
public class AdministrateService {
    //CONSTANTS
    private static final String ROLLBACK_ERROR
            = "it is impossible to rollback transaction";
    private static final String USER_DAO = "userDao";
    private static final String PROCEDURE_DAO = "procedureDao";

    private static final Logger LOGGER
            = LogManager.getLogger(AdministrateService.class);

    /**
     * This method allows you to get the number of pages.
     * @param tab tab with specific entities
     * @return int number of pages.
     * @throws ServiceException
     */
    public int getPagecount(int tab) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        int pageCount = 0;
        try {
            transactionFactory = new TransactionFactoryImpl();
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
    public List<User> administrateUsers(int paginationPage) throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<User> users = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            UserDao userDao = transaction.createDao(DaoEnum.USER.getDao());
            users = userDao.findInterval((paginationPage - 1) * 10, 10);
            for (User user : users) {
                try {
                    roleDao.findByUser(user).stream().forEach(user::addRole);
                } catch (DaoException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
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
    public List<AppointmentDto> administrateAppointments(int paginationPage)
            throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            CategoryDao categoryDao = transaction.createDao("categoryDao");
            AppointmentDao appointmentDao
                    = transaction.createDao("appointmentDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            UserDao userDao = transaction.createDao(USER_DAO);
            ProcedureDao procedureDao = transaction.createDao(PROCEDURE_DAO);
            List<Category> categories = categoryDao.findall();
            List<Appointment> appointments = appointmentDao
                    .findInterval((paginationPage - 1) * 10, 10);
            Set<ProcedureEmployee> procedureEmployeeList
                    = appointments.stream().map(appointment -> {
                        try {
                            return procedureEmployeeDao
                                    .findById(appointment.getProcedureEmployeeId());
                        } catch (DaoException e) {
                            LOGGER.warn(String.format("an error occurred while " +
                                            "getting procedureEmployee by id: %d"
                                    , appointment.getProcedureEmployeeId()));
                        }
                        return null;
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            Set<Procedure> procedures = procedureEmployeeList.stream()
                    .map(procedureEmployee -> {
                        try {
                            Procedure procedure = procedureDao
                                .findById(procedureEmployee.getProcedureId());
                            procedure.setId(procedureEmployee.getId());
                            return procedure;
                        } catch (DaoException e) {
                            LOGGER.warn(String.format("an error occured " +
                                    "while getting procedure by id: %d",
                                    procedureEmployee.getProcedureId()));
                        }
                        return null;
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            Set<User> employees = procedureEmployeeList.stream()
                    .map(procedureEmployee -> {
                        try {
                            User employee = userDao
                                .findById(procedureEmployee.getEmployeeId());
                            return employee;
                        } catch (DaoException e) {
                            LOGGER.warn(String.format("an error ocured " +
                                            "while getting employee by id: %d",
                                    procedureEmployee.getEmployeeId()));
                        }
                        return null;
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            Set<User> clients = appointments.stream().map(appointment -> {
                try {
                    return userDao.findById(appointment.getUserId());
                } catch (DaoException e) {
                    LOGGER.warn("an error occurred " +
                                    "while getting client by id: {}",
                            appointment.getUserId());
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toSet());
            List<AppointmentDto> appointmentDtoList = new ArrayList<>();

            appointments.stream().forEach(appointment -> {
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.setId(appointment.getId());
                appointmentDto.setStatus(appointment.getStatus());
                appointmentDto.setDate(appointment.getDate());
                appointmentDto.setPrice(appointment.getPrice());
                appointmentDto.setClient(clients.stream()
                        .filter(client -> client.getId() == appointment.getUserId())
                        .findAny().get().getName());
                appointmentDto.setEmployee(employees.stream()
                        .filter(employee -> employee.getId() == procedureEmployeeList.stream()
                                .filter(procedureEmployee -> procedureEmployee.getId() == appointment.getProcedureEmployeeId())
                                .findAny().get().getEmployeeId())
                        .findAny().get().getName());
                appointmentDto.setProcedure(procedures.stream()
                        .filter(procedure -> procedure.getId() == procedureEmployeeList.stream()
                                .filter(procedureEmployee -> procedureEmployee.getId() == appointment.getProcedureEmployeeId())
                                .findAny().get().getProcedureId())
                        .findAny().get().getName());
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
    public List<ProcedureDto> administrateProcedures(int paginationPage)
            throws ServiceException {
        TransactionFactory transactionFactory;
        Transaction transaction = null;
        List<Entity> entities;
        try {
            transactionFactory = new TransactionFactoryImpl();
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
     * @throws ServiceException
     */
    public List<Entity> administrateSchedules(int paginationPage)
            throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<Entity> entities = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScheduleDao scheduleDao = transaction.createDao("scheduleDao");
            UserDao userDao = transaction.createDao(USER_DAO);

            List<Schedule> schedules = scheduleDao
                    .findInterval((paginationPage - 1) * 10, 10);
            List<User> employees = userDao.findEmployees();
            entities = new ArrayList<>();
            entities.addAll(employees);
            entities.addAll(schedules);
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
        return entities;
    }
}
