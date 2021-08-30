package by.training.task4.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Country extends AdministrativeUnit{
    private Set<Region> regions = new HashSet<Region>();
    private City capital;

    public Country(String name) {
        this.name = name;
    }
    public City getCapital() {
        return capital;
    }
    public boolean setCapital(String capital) {
        for (Region r : regions) {
            for (District d : r.getDistricts()) {
                if (d.getCity(capital) != null) {
                    this.capital = d.getCity(capital);
                    return true;
                }
            }
        }
        return false;
    }
    public int getPopulation() {
        int tempPopulation = 0;
        for (Region r : regions) {
            tempPopulation += r.getPopulation();
        }
        return tempPopulation;
    }
    public double getArea() {
        double tempSquare = 0;
        for (Region r : regions) {
            tempSquare += r.getArea();
        }
        return tempSquare;
    }
    public Set<City> getRegionCenteres() {
        Set<City> cities = new HashSet<>();
        for (Region r : regions) {
            cities.add(r.getRegionCenter().getDistrictCenter());
        }
        return cities;
    }
    public Region getRegion(String name) {
        for (Region region : regions) {
            if (region.getName().equals(name)) {
                return region;
            }
        }
        return null;
    }
    public District getDistrict(String name) {
        District district = null;
        for (Region region : regions) {
            district = region.getDistrict(name);
            if(district!=null){
                break;
            }
        }
        return district;
    }
    public City getCity(String name){
        City city = null;
        for (Region region:regions){
            city = region.getCity(name);
            if(city!=null){
                break;
            }
        }
        return city;
    }

    public void addRegion(Region region) {
        regions.add(region);
    }
    public void clear() {
        name = null;
        capital = null;
        regions.clear();
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
        return "Country" +
                "\nName: " + name +
                "\nCapital: " + (capital!=null? capital.name:"") +
                "\nArea: " + getArea() +
                "\nPopulation: " + getPopulation() +
                "\n" + regions.toString();
    }


}
