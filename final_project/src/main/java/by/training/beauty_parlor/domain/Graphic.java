package by.training.beauty_parlor.domain;

import java.time.LocalDateTime;

public class Graphic extends Entity{
    private int id;
    private int employeeId;
    private LocalDateTime date;

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "id: " + id + "employeeId: " + employeeId + "date: " + date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graphic graphic = (Graphic) o;
        return id == graphic.id;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
