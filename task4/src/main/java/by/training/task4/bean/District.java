package by.training.task4.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class District {
    private String name;

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    private double square;
    private ArrayList<City> cites = new ArrayList<>();
    public String getName() {
        return name;
    }
    public City getDistrictCenter() {
        return districtCenter;
    }

    private City districtCenter;
    public District(String name, City districtCenter){
        this.districtCenter = districtCenter;
        cites.add(districtCenter);
        this.name = name;
        this.square = 100;
    }
    public District(String name, City districtCenter,double square){
        this.districtCenter = districtCenter;
        this.name = name;
        this.square = square;
    }
    public void addCity(City city){
        cites.add(city);
    }
    public City getCity(String name){
        for (City c:cites){
            if(c.getName()==name){
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
        tempPopulation+=districtCenter.getPopulation();
        return tempPopulation;
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
