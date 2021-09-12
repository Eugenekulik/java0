package by.training.task5.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();


    public ServiceFactory getInstance(){
        return instance;
    }
}
