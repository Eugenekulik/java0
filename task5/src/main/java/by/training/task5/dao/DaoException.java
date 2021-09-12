package by.training.task5.dao;

import java.io.FileNotFoundException;

public class DaoException extends Exception {
    public DaoException(){
        super();
    }
    public DaoException(Throwable e) {
        super(e);
    }
    public DaoException(String message){
        super(message);
    }
    public DaoException(String message,Throwable e){
        super(message,e);
    }
}
