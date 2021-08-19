package by.training.task3.service;

/**
 * This class is exception for service layer
 */
public class ServiceException extends Exception{
    public ServiceException(){};
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable exception){

    }
    public ServiceException(String message,Throwable exception){
        super(message,exception);
    }
}
