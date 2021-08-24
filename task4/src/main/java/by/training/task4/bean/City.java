package by.training.task4.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class City {
    private String name;
    private int population;
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public City(String name) {
        this.name = name;
        this.population = 0;
    }
    public City(String name,int population) {
        this.name = name;
        this.population = population;
    }
    public String getName() {
        return name;
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

