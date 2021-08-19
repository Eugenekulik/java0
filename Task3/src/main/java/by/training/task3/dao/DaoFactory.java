package by.training.task3.dao;

/**
 * Factory for Dao layer
 */
public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();


    public DaoFactory getInstance(){
        return instance;
    }
}
