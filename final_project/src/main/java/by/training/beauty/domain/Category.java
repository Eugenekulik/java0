package by.training.beauty.domain;

import java.util.Objects;

/**
 * This class extends Entity and represents bean.
 * It represents the group of the procedures which have common technologies.
 * it has fields:
 * name - name of the category;
 * description - description of the category.
 */

public class Category extends Entity{
    private String name;
    private String description;

    public Category() {
    }

    public Category(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return getId() == category.getId() && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @Override
    public String toString(){
        return "id: " + getId() + "name: " + name;
    }

    public static class Builder {
        private int id;
        private String name;
        private String description;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Category build(){
            return new Category(this);
        }

    }
}
