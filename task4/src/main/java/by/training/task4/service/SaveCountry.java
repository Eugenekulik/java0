package by.training.task4.service;

import by.training.task4.Dao.CountryDao;
import by.training.task4.Dao.DaoException;
import by.training.task4.Dao.DaoFactory;
import by.training.task4.bean.Country;

public class SaveCountry {
    public SaveCountry(){}

    public void save(Country country, String file)throws ServiceException {
        CountryDao countryDao = DaoFactory.getInstance().getCountryDao();
        try {
            countryDao.init(file);
            countryDao.write(country);
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
