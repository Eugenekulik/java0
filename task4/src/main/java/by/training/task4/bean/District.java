package by.training.task4.bean;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

/**
 * Class District extands AdministrativeUnit and realize entity - district
 * It have all atributes of AdministrativeUnit and also area, and set of cities
 */




public class District extends AdministrativeUnit{
    private City districtCenter;
    private double area;
    private Set<City> cites = new HashSet<>();

    public District(String name){
        this.name = name;
        this.area = 100;
    }

    public double getArea() {
        return area;
    }
    public void setArea(double area) {
        if(area>0) {
            this.area = area;
        }
    }
    public City getDistrictCenter() {
        return districtCenter;
    }
    public boolean setDistrictCenter(String name) {
        City city = getCity(name);
        if(city!=null){
            districtCenter = city;
            return true;
        }
        return false;
    }
    public City getCity(String name){
        for (City c:cites){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }
    public int getPopulation(){
        int tempPopulation=0;
        for (City c:cites) {
            tempPopulation+=c.getPopulation();
        }
        return tempPopulation;
    }

    public void addCity(City city){
        cites.add(city);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public String toString() {
        return "District" +
                "\nName: " + name +
                "\nDistrict center: " + (districtCenter!=null? districtCenter.name:"") +
                "\nArea: " + area +
                "\nPopulation: " + getPopulation() +
                "\n" + cites.toString();
    }


}
