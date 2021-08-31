package by.training.task4.service;

import by.training.task4.service.change.ChangeCity;
import by.training.task4.service.change.ChangeCountry;
import by.training.task4.service.change.ChangeDistrict;
import by.training.task4.service.change.ChangeRegion;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private CreateCountry createCountry = new CreateCountry();
    private SaveCountry saveCountry = new SaveCountry();

    public ChangeCountry getChangeCountry() {
        return changeCountry;
    }
    public ChangeDistrict getChangeDistrict() {
        return changeDistrict;
    }
    public ChangeCity getChangeCity() {
        return changeCity;
    }
    public ChangeRegion getChangeRegion() {
        return changeRegion;
    }
    private ChangeCountry changeCountry = new ChangeCountry();
    private ChangeDistrict changeDistrict = new ChangeDistrict();
    private ChangeCity changeCity = new ChangeCity();
    private ChangeRegion changeRegion = new ChangeRegion();
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
