package by.training.beauty.service;

import by.training.beauty.service.validator.UserValidator;

public class ServiceFactory {
    private static  final ServiceFactory INSTANCE = new ServiceFactory();
    private final UserValidator userValidator = new UserValidator();
    private final AdministrateService administrateService = new AdministrateService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final ConnectionPoolService connectionPoolService = new ConnectionPoolService();
    private final ScheduleService scheduleService = new ScheduleService();
    private final ProcedureService procedureService = new ProcedureService();
    private final UserService userService = new UserService();
    private final ScoreService scoreService = new ScoreService();
    private final RoleService roleService = new RoleService();


    public static ServiceFactory getInstance(){
        return INSTANCE;
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

