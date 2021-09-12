package by.training.task5.controller.command;

public class ManagerCommand {
    private Command command;
    public ManagerCommand(Command command){
        this.command = command;
    }
    public void invokeCommand(){
        command.execute();
    }
}
