package by.training.beauty.controller.action;

import by.training.beauty.controller.action.implementation.admin.AdministrateAction;
import by.training.beauty.controller.action.implementation.admin.AdministrateAddAction;
import by.training.beauty.controller.action.implementation.admin.AdministrateChangeAction;
import by.training.beauty.controller.action.implementation.admin.ScheduleAddAction;
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
    ADMINISTRATE_CHANGE(AdministrateChangeAction.class),
    SHEDULE_ADD(ScheduleAddAction.class),


    //Client
    APPOINTMENT_ADD(AppointmentAddAction.class),
    ADD_SCORE(AddScoreAction.class),
    APPOINTMENT(AppointmentAction.class),
    PROCEDURE(ProcedureAction.class);

    private Class<? extends Action> action;

    ActionEnum(Class<? extends Action> action){
        this.action = action;
    }

    public Class<? extends Action> getAction(){
        return action;
    }
}
