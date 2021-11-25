package by.training.beauty_parlor.service;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public AppointmentService getAppointmentService(){
        return new AppointmentService();
    }
    public ConnectionPoolInitService getConnectionPoolInitService(){
        return new ConnectionPoolInitService();
    }
    public EmployeeService getEmployeeService(){
        return new EmployeeService();
    }
}
