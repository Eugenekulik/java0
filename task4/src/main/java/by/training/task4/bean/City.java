package by.training.task4.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class City extands AdministrativeUnit and realize entity - city
 * It have all atributes of AdministrativeUnit and also population
 */

public class City extends AdministrativeUnit {
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
        return "City" +
                "\nName: " + name +
                "\nPopulation: " + population;
    }
}

