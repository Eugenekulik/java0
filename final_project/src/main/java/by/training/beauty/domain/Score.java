package by.training.beauty.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class extends Entity and represents bean.
 * It represents score and comment which client can leave.
 * It has fields:
 * userId - id of the user which leave this score;
 * appointmentId - id of the appointment which store belongs;
 * comment  - text comment of the store;
 * date - date of the store;
 * value  - number from 1 to 5.
 */

public class Score extends Entity {
    private int userId;
    private int appointmentId;
    private String comment;
    private LocalDateTime date;
    private byte value;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return getId() == score.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "id: " + getId() + "user_id: " +
                userId + "appointment_id: " + appointmentId + "date: " + date;
    }
}
