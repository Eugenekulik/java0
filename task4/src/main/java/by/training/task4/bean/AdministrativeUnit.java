package by.training.task4.bean;

import java.io.Serializable;

/**
 * This class realize any administrative unit
 * It have name
 */
public abstract class AdministrativeUnit implements Serializable {
    protected String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
