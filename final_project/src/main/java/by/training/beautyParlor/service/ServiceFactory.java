package by.training.beautyParlor.service;

import by.training.beautyParlor.service.validator.UserValidator;

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
    public GraphicService getGraphicService(){
        return new GraphicService();
    }
    public ProcedureService getProcedureService(){
        return new ProcedureService();
    }
    public UserService getUserService(){
        return new UserService();
    }

}

