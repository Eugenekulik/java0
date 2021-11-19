package by.training.beauty_parlor.domain;

import java.util.Objects;

public class ProcedureEmployee extends Entity {
    private int employeeId;
    private int procedureId;
    private int rating;
    private int price;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcedureEmployee that = (ProcedureEmployee) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString(){
        return "id: " + getId() + "employeeId: " + employeeId +
                "procedureId: " + procedureId + "rating: " + rating + "price: " + price;
    }
}
