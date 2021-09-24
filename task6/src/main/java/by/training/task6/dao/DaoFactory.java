package by.training.task6.dao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    public DaoFactory getInstance() {
        return instance;
    }
}
