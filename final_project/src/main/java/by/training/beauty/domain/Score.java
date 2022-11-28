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
    private int value;

    public Score(){}
    public Score(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setAppointmentId(builder.appointmentId);
        setComment(builder.comment);
        setDate(builder.date);
        setValue(builder.value);
    }

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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

    public static class Builder {
        private int id;
        private int userId;
        private int appointmentId;
        private String comment;
        private LocalDateTime date;
        private int value;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder setValue(int value) {
            this.value = value;
            return this;
        }

        public Score build(){
            return new Score(this);
        }
    }
}
