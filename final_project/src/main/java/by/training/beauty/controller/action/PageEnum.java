package by.training.beauty.controller.action;

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
    APPOINTMENT_CREATE_FORM("/WEB-INF/jsp/appointment_create_form.jsp"),

    //Admin
    ADMINISTRATE("/WEB-INF/jsp/administrate.jsp"),
    ADMINISTRATE_SCHEDULE_ADD("/WEB-INF/jsp/schedule_add.jsp"),
    PROCEDURE_FORM_CREATE("/WEB-INF/jsp/procedure_create_form.jsp"),
    PROCEDURE_FORM_UPDATE("/WEB-INF/jsp/procedure_update_form.jsp"),

    //Employee
    SCHEDULE("/WEB-INF/jsp/schedule.jsp");

    public String getPage() {
        return page;
    }

    private final String page;
    PageEnum(String page) {
        this.page = page;
    }

}
