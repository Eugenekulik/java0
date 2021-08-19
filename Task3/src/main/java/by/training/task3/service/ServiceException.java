package by.training.task3.service;

public class ServiceException extends Exception{
    public ServiceException(){};
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable exception){

    }
}
