package by.training.task4.service.change;

import by.training.task4.bean.City;
import by.training.task4.service.ServiceException;

public class ChangeCity {
    public ChangeCity(){};
    public void name(City city, String name){
        city.setName(name);
    }
    public void population(City city, String population) throws ServiceException {
        try {
            city.setPopulation(Integer.parseInt(population));
        }
        catch (NumberFormatException e){
            throw new ServiceException(e);
        }
    }
}
