package by.training.beauty;

import by.training.beauty.controller.action.ActionEnum;

public class Main {
    public static void main(String[] args) {
        ActionEnum actionEnum = ActionEnum.valueOf("MAIN");
        System.out.println(actionEnum.getAction().getName());
    }
}
