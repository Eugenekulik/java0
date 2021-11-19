package by.training.beauty_parlor.domain;

import java.io.Serializable;

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
