package by.training.task3.dao;

public class DaoException extends Exception{
    public DaoException(){
        super();
    }
    public DaoException(String message){
        super(message);
    }
    public DaoException(Throwable exception){
        super(exception);
    }
    public DaoException(String message,Throwable exception){
        super(message,exception);
    }
}
