package by.training.task4.Dao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private CountryDao countryDao = new CountryDao();

    public static DaoFactory getInstance(){
        return instance;
    }
    public CountryDao getCountryDao(){
        return countryDao;
    }
}
