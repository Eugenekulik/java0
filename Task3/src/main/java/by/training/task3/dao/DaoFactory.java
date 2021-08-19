package by.training.task3.dao;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();


    public DaoFactory getInstance(){
        return instance;
    }
}
