package by.training.task4.service;

import by.training.task4.Dao.CountryDao;
import by.training.task4.Dao.DaoException;
import by.training.task4.Dao.DaoFactory;
import by.training.task4.bean.Country;

public class CreateCountry {
    private Country country;
    public CreateCountry(){}
    public Country openCountry(String filePath) throws ServiceException {
        try {
            CountryDao countryDao = DaoFactory.getInstance().getCountryDao();
            countryDao.init(filePath);
            country = countryDao.read();
            return country;
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
    }
    public Country createCountry(String name){
        return country = new Country(name);
    }
}
