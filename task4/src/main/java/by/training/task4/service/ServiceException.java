package by.training.task4.service;

public class ServiceException extends Exception{
    public ServiceException(){
        super();
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable exception){
        super(exception);
    }
    public ServiceException(String message,Throwable exception){
        super(message,exception);
    }
}
