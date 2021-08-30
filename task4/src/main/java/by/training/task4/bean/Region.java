package by.training.task4.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Region extands AdministrativeUnit and realize entity - region
 * It have all atributes of AdministrativeUnit and also set of districts
 */

public class Region extends  AdministrativeUnit{
    private Set<District> districts = new HashSet<>();
    private District regionCenter;

    public Region(String name){
        this.name = name;

    }

    public boolean setRegionCenter(String name) {
        District district = getDistrict(name);
        if(district!=null){
            regionCenter = district;
            return true;
        }
        return false;
    }
    public District getRegionCenter() {
        return regionCenter;
    }
    public int getPopulation(String key){
        if(regionCenter.getName()==key){
            return regionCenter.getPopulation();
        }
        for (District d:districts) {
            if(d.getName()==key){
                return d.getPopulation();
            }
        }
        return 0;
    }
    public int getPopulation(){
        int tempPopulation=0;
        for (District d:districts) {
            tempPopulation+=d.getPopulation();
        }
        return tempPopulation;
    }
    public double getArea(String key){
        if(regionCenter.getName()==key){
            return regionCenter.getArea();
        }
        for (District d:districts) {
            if(d.getName()==key){
                return d.getArea();
            }
        }
        return 0;
    }
    public double getArea(){
        double tempPopulation=0;
        for (District d:districts) {
            tempPopulation+=d.getArea();
        }
        return tempPopulation;
    }
    public District getDistrict(String name){
        for (District district:districts) {
            if(district.getName().equals(name)){
                return district;
            }
        }
        return null;
    }
    public Set<District> getDistricts(){
        return districts;
    }
    public City getCity(String name){
        City city = null;
        for(District district:districts){
            city = district.getCity(name);
            if(city!=null){
                break;
            }
        }
        return city;
    }

    public void addDistrict(District district){
        districts.add(district);
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
        return "Region" +
                "\nName: " + name +
                "\nRegion center: " + (regionCenter!=null? regionCenter.name:"") +
                "\nArea: " + getArea() +
                "\nPopulation: " + getPopulation() +
                districts.toString();
    }


}
