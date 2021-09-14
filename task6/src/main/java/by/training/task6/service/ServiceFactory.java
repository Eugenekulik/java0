package by.training.task6.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private EllipseCreator ellipseCreator;

    public static ServiceFactory getInstance(){
        return instance;
    }
    public EllipseCreator getEllipseCreator(){
        if(ellipseCreator == null){
            ellipseCreator = new EllipseCreator();
        }
        return ellipseCreator;
    }
}
