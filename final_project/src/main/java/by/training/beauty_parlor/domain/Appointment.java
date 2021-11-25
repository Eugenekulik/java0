package by.training.beauty_parlor.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment extends Entity{
    private int procedureEmployeeId;
    private int userId;
    private double price;
    private int status;
    private LocalDateTime date;

    public int getProcedureEmployeeId() {
        return procedureEmployeeId;
    }

    public void setProcedureEmployeeId(int procedureEmployeeId) {
        this.procedureEmployeeId = procedureEmployeeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "id: " + getId() + "procedureEmployeeId: " + procedureEmployeeId +
                "userId: " + userId + "date: "  +
                        " status: " + status + "price: " + price;// TODO: 22.11.2021  
    }
}
