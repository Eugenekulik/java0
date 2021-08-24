package by.training.task4.bean;

import java.util.ArrayList;

public class Country {
    public City getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        for (Region r:regions) {
            for (District d:r.getDistricts()) {
                if(d.getCity(capital)!=null){
                    this.capital = d.getCity(capital);
                }
            }
        }
    }

    private City capital;
    public int getPopulation(){
        int tempPopulation = 0;
        for (Region r:regions) {
            tempPopulation+=r.getPopulation();
        }
        return tempPopulation;
    }
    public double getSquare(){
        double tempSquare = 0;
        for (Region r:regions){
            tempSquare += r.getSquare();
        }
        return tempSquare;
    }
    private String name;
    private ArrayList<Region> regions = new ArrayList<>();
    public ArrayList<City> getRegionCenteres(){
        ArrayList<City> cities =new ArrayList<>();
        for (Region r:regions) {
            cities.add(r.getRegionCenter().getDistrictCenter());
        }
        return cities;
    }
    public Country(String name){
        this.name = name;
    }
    public void addRegion(Region region){
        regions.add(region);
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
        return name;
    }
}
