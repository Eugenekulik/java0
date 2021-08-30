package by.training.task4.service;

import by.training.task4.Dao.DaoFactory;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private CreateCountry createCountry = new CreateCountry();
    private SaveCountry saveCountry = new SaveCountry();
    public static ServiceFactory getInstance(){
        return instance;
    }
    public CreateCountry getCreateCountry() {
        return createCountry;
    }
    public SaveCountry getSaveCountry(){
        return saveCountry;
    }
}
