package by.training.beauty_parlor.controller.action;

public enum PageEnum {
    LOGIN("/WEB-INF/jsp/login.jsp"),
    REGISTRATION("/WEB-INF/jsp/registration.jsp"),
    PROCEDURE("/WEB-INF/jsp/procedure.jsp"),
    ERROR("/WEB-INF/jsp/error.jsp"),
    MAIN("/WEB-INF/jsp/main.jsp");
    public String getPage() {
        return page;
    }

    private String page;
    PageEnum(String page) {
        this.page = page;
    }

}
