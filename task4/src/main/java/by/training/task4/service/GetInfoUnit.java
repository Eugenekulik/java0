package by.training.task4.service;

import by.training.task4.bean.AdministrativeUnit;
import by.training.task4.bean.Country;

import java.util.ArrayList;
import java.util.List;


public class GetInfoUnit {
    private Country country;
    public GetInfoUnit(Country country){
        this.country = country;
    }
    public String getInfo(String name){
        List<AdministrativeUnit> administrativeUnits = new ArrayList<>();
        if(country.getName().equals(name)){
            administrativeUnits.add(country);
        }
        if(country.getRegion(name)!=null) {
            administrativeUnits.add(country.getRegion(name));
        }
        if(country.getDistrict(name)!=null) {
            administrativeUnits.add(country.getDistrict(name));
        }
        if(country.getCity(name)!=null) {
            administrativeUnits.add(country.getCity(name));
        }
        if(administrativeUnits.size()==0){
            return "no unit";
        }
        StringBuilder units = new StringBuilder();
        for (AdministrativeUnit a:administrativeUnits){
            units.append(a.toString());
        }
        return units.toString();
    }
}
