package by.training.task2.controller;

import by.training.task2.command.*;
import by.training.task2.service.CurrentString;
import by.training.task2.service.MathTask;
import by.training.task2.view.Messanger;
import by.training.task2.view.Reader;

import java.util.Locale;

public class Runner {

    /**
     * Running method of the application
     * @param args
     */
    public static void main(String[] args) {
        Messanger userOut = new Messanger("ru","Ru");
        Receiver receiver = new Receiver(new String[]{"5","10","1"});
        Client client = new Client(receiver);
        Command command = client.initCommand(CommandType.CALCFUNCTABLE);
        ManagerCommand invoker = new ManagerCommand(command);
        invoker.invokeCommand();
        receiver.getResult());
    }
}
