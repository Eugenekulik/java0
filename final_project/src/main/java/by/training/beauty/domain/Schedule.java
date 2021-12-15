package by.training.beauty.domain;

import java.time.LocalDateTime;

/**
 * This class extends Entity and represents bean.
 * Is represents an object that stores information about the employee's working time.
 * It has fields:
 * employeeId - id of the employee;
 * date - working time.
 */

public class Schedule extends Entity{
    private int employeeId;
    private LocalDateTime date;

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "id: " + getId() + "employeeId: " + employeeId + "date: " + date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return getId() == schedule.getId();
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
