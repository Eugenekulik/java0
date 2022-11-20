package by.training.beauty.controller.action;

import by.training.beauty.controller.action.implementation.admin.*;
import by.training.beauty.controller.action.implementation.client.*;
import by.training.beauty.controller.action.implementation.common.*;

public enum ActionEnum {

    //Common
    EMPTY(EmptyAction.class),
    LOGIN(LoginAction.class),
    LOGIN_FORM(LoginFormAction.class),
    REGISTRATION(RegistrationAction.class),
    LOGOUT(LogoutAction.class),
    MAIN(MainAction.class),

    //Admin
    ADMINISTRATE(AdministrateAction.class),
    ADMINISTRATE_ADD(AdministrateAddAction.class),
    SCHEDULE_ADD(ScheduleCreateFormAction.class),
    ADMINISTRATE_USER(AdministrateUserAction.class),
    ADMINISTRATE_APPOINTMENT(AdministrateAppointmentAction.class),
    ADMINISTRATE_PROCEDURE(AdministrateProcedureAction.class),
    ADMINISTRATE_SCHEDULE(AdministrateScheduleAction.class),



    //Client
    APPOINTMENT_ADD(AppointmentAddAction.class),
    SCORE(ScoreAction.class),
    APPOINTMENT(AppointmentAction.class),
    PROCEDURE(ProcedureAction.class);

    private final Class<? extends Action> action;

    ActionEnum(Class<? extends Action> action){
        this.action = action;
    }

    public Class<? extends Action> getAction(){
        return action;
    }
}
