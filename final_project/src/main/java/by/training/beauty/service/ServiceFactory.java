package by.training.beauty.service;

import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.service.validator.UserValidator;

public class ServiceFactory {
    private static  final ServiceFactory INSTANCE = new ServiceFactory();
    private final TransactionFactory transactionFactory = new TransactionFactoryImpl();

    private final UserValidator userValidator = new UserValidator();
    private final AdministrateService administrateService = new AdministrateService(transactionFactory);
    private final AppointmentService appointmentService = new AppointmentService(transactionFactory);
    private final ConnectionPoolService connectionPoolService = new ConnectionPoolService();
    private final ScheduleService scheduleService = new ScheduleService(transactionFactory);
    private final ProcedureService procedureService = new ProcedureService(transactionFactory);
    private final UserService userService = new UserService(transactionFactory);
    private final ScoreService scoreService = new ScoreService(transactionFactory);
    private final RoleService roleService = new RoleService(transactionFactory);
    private final CategoryService categoryService = new CategoryService(transactionFactory);


    public static ServiceFactory getInstance(){
        return INSTANCE;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }
    public AdministrateService getAdministrateService(){
        return administrateService;
    }
    public AppointmentService getAppointmentService(){
        return appointmentService;
    }
    public ConnectionPoolService getConnectionPoolService(){
        return connectionPoolService;
    }
    public UserValidator getUserValidator(){
        return userValidator;
    }
    public ScheduleService getScheduleService(){
        return scheduleService;
    }
    public ProcedureService getProcedureService(){
        return procedureService;
    }
    public UserService getUserService(){
        return userService;
    }
    public ScoreService getScoreService() {return scoreService;}
    public RoleService getRoleService() {return roleService;}

}

