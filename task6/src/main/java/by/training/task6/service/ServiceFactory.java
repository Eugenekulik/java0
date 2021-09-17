package by.training.task6.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private CubeCreator cubeCreator;
    public static ServiceFactory getInstance(){
        return instance;
    }
    public CubeCreator getEllipseCreator(){
        if(cubeCreator == null){
            cubeCreator = new CubeCreator();
        }
        return cubeCreator;
    }
}
