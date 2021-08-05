package by.training.task2.command;

import java.util.HashMap;

public class ManagerCommand {


    private Command mCommand;
    public ManagerCommand(Command command) {
        mCommand = command;
    }
    public void invokeCommand() {
        System.out.println("Refer to command for execution");
        mCommand.execute();
    }
}
