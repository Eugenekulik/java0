package by.training.task4.service.Change;

import by.training.task4.bean.City;
import by.training.task4.bean.Country;

public class ChangeCountry {
    public ChangeCountry(){}
    public void name(Country country,String name){
        country.setName(name);
    }
    public boolean capital(Country country,String name){
        return country.setCapital(name);
    }
}
