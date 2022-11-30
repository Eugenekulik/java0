package by.training.beauty.dao.mysql;

public enum DaoEnum {
    ROLE("roleDao"),
    USER("userDao"),
    PROCEDURE("procedureDao"),
    SCORE("scoreDao"),
    APPOINTMENT("appointmentDao"),
    SCHEDULE("scheduleDao"),
    CATEGORY("categoryDao");

    private final String dao;

    DaoEnum(String dao) {
        this.dao = dao;
    }

    public String getDao(){
        return dao;
    }
}
