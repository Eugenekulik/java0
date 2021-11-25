package by.training.beauty_parlor.controller.action;

public enum PageEnum {
    //Common
    LOGIN("/WEB-INF/jsp/login.jsp"),
    REGISTRATION("/WEB-INF/jsp/registration.jsp"),
    PROCEDURE("/WEB-INF/jsp/procedure.jsp"),
    ERROR("/WEB-INF/jsp/error.jsp"),
    MAIN("/WEB-INF/jsp/main.jsp"),
    ABOUT("/WEB-INF/jsp/about.jsp"),

    //Client
    APPOINTMENT("/WEB-INF/jsp/appointment.jsp"),
    APPOINTMENT_ADD("/WEB-INF/jsp/appointment_add.jsp"),

    //Admin
    ADMINISTRATE("/WEB-INF/jsp/administrate.jsp"),

    //Employee
    GRAPHIC("/WEB-INF/jsp/graphic.jsp");

    public String getPage() {
        return page;
    }

    private String page;
    PageEnum(String page) {
        this.page = page;
    }

}
