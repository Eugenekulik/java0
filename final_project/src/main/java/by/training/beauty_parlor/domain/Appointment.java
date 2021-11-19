package by.training.beauty_parlor.domain;

import java.sql.Date;
import java.util.Objects;

public class Appointment extends Entity{
    private int procedureEmployeeId;
    private int userId;
    private int price;
    private boolean status;
    private Date date;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
                "userId: " + userId + "date: " + date.getTime() +
                        " status: " + status + "price: " + price;
    }
}
