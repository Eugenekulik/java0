package by.training.task4.bean;

import by.training.task4.view.Reader;

import java.util.ArrayList;

public class Region {
    private ArrayList<District> districts = new ArrayList<>();
    private String name;
    private District regionCenter;
    public String getName() {
        return name;
    }
    public District getRegionCenter() {
        return regionCenter;
    }
    public Region(String name,District regionCenter){
        this.name = name;
        this.regionCenter = regionCenter;
        districts.add(regionCenter);
    }
    public void addDistrict(District district){
        districts.add(district);
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
        tempPopulation+= regionCenter.getPopulation();
        return tempPopulation;
    }
    public double getSquare(String key){
        if(regionCenter.getName()==key){
            return regionCenter.getSquare();
        }
        for (District d:districts) {
            if(d.getName()==key){
                return d.getSquare();
            }
        }
        return 0;
    }
    public double getSquare(){
        double tempPopulation=0;
        for (District d:districts) {
            tempPopulation+=d.getSquare();
        }
        tempPopulation+= regionCenter.getSquare();
        return tempPopulation;
    }
    public ArrayList<District> getDistricts(){
        return districts;
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
