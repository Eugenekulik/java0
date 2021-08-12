package by.training.task2.service;

/**
 * This class is exception which catch every exception in Service laeyer
 */
public class ServiceException extends Exception{
    public ServiceException(){
    }
    public ServiceException(String message, Throwable exception){
        super(message,exception);
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable exception){
        super(exception);
    }
}
