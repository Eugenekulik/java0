package by.training.beauty.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class extends Entity and represents bean.
 * It represents the appointment which client can do.
 * it has fields:
 * userId - the id of the user who submitted the entry;
 * price - price of work;
 * status - what is the state of appointment(1 - in processing, 2 - active, 3 - archive, 4 - cancelled);
 * date - the date of the appointment.
 */

public class Appointment extends Entity{
    private Procedure procedure;
    private User employee;
    private int userId;
    private double price;
    private int status;
    private LocalDateTime date;


    public Appointment(){}
    public Appointment(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setDate(builder.date);
        setPrice(builder.price);
        setStatus(builder.status);
        setProcedure(builder.procedure);
        setEmployee(builder.employee);
    }


    public Procedure getProcedure() {
        return procedure;
    }

    public Appointment setProcedure(Procedure procedure) {
        this.procedure = procedure;
        return this;
    }

    public User getEmployee() {
        return employee;
    }

    public Appointment setEmployee(User employee) {
        this.employee = employee;
        return this;
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
        return "id: " + getId() +
                "userId: " + userId + "date: "  + date +
                        " status: " + status + "price: " + price;
    }


    public static class Builder{
        private int id;
        private int userId;
        private int status;
        private double price;
        private LocalDateTime date;
        private Procedure procedure;
        private User employee;



        public Builder setProcedure(Procedure procedure) {
            this.procedure = procedure;
            return this;
        }

        public Builder setEmployee(User employee) {
            this.employee = employee;
            return this;
        }
        public Builder setId(int id){
            this.id = id;
            return this;
        }
        public Builder setUserId(int userId){
            this.userId = userId;
            return this;
        }
        public Builder setPrice(double price){
            this.price = price;
            return this;
        }
        public Builder setStatus(int status){
            this.status = status;
            return this;
        }
        public Builder setDate(LocalDateTime date){
            this.date = date;
            return this;
        }
        public Appointment build(){
            return new Appointment(this);
        }
    }
}
