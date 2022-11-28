package by.training.beauty.domain;

import java.util.Objects;

public class Role extends Entity{
    private String name;

    public Role(int id, String name){
        setId(id);
        this.name = name;
    }
    public Role(String name) {
        this.name = name;
    }
    public Role(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static class Builder {
    }
}
