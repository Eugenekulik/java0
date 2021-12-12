package by.training.beautyParlor.domain;

import java.io.Serializable;

/**
 * It's base bean class of application.
 * It implemented interface Serializable and Cloneable.
 */
public abstract class Entity implements Serializable, Cloneable {
    private int id;
    public Entity(){
    }
    public Entity(int newId){
        id = newId;
    }
    public int getId(){
        return id;
    }
    public void setId(int newId){
        id = newId;
    }
}
