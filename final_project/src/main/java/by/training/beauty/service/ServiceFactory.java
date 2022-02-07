package by.training.beauty.service;

import by.training.beauty.domain.Score;
import by.training.beauty.service.validator.UserValidator;

public class ServiceFactory {
    private static  final ServiceFactory INSTANCE = new ServiceFactory();
    private final UserValidator userValidator = new UserValidator();
    public static ServiceFactory getInstance(){
        return INSTANCE;
    }

    public AdministrateService getAdministrateService(){
        return new AdministrateService();
    }
    public AppointmentService getAppointmentService(){
        return new AppointmentService();
    }
    public ConnectionPoolService getConnectionPoolService(){
        return new ConnectionPoolService();
    }
    public UserValidator getUserValidator(){
        return userValidator;
    }
    public EmployeeService getEmployeeService(){
        return new EmployeeService();
    }
    public ScheduleService getscheduleService(){
        return new ScheduleService();
    }
    public ProcedureService getProcedureService(){
        return new ProcedureService();
    }
    public UserService getUserService(){
        return new UserService();
    }
    public ScoreService getScoreService() {return new ScoreService();}

}

