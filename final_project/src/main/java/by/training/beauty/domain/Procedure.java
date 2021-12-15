package by.training.beauty.domain;

import java.util.Objects;

/**
 * This class extends Entity and represents bean.
 * It represents one of the possible procedures of the beauty parlor.
 * It has fields:
 * name - name of the procedure;
 * description - description of the procedure;
 * elapsedTime - time required for the procedure;
 * categoryId - id of the category to which the procedure belongs.
 */

public class Procedure extends Entity{
    private int categoryId;
    private String name;
    private String description;
    private int elapsedTime;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return getId() == procedure.getId() && Objects.equals(name, procedure.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @Override
    public String toString(){
        return "id: " + getId() + "name: " + name + "categoryId: " + categoryId +
                "elapsedTime: " + elapsedTime;
    }
}
