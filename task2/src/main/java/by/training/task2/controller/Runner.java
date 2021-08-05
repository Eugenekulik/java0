package by.training.task2.controller;

import by.training.task2.command.*;

public class Runner {
    /**
     * Running method of the application
     * @param args
     */
    public static void main(String[] args) {
        String[] data= {"4","5","3","6" };
        Receiver receiver =new Receiver(data);
        Client client = new Client(receiver);

        Command command = client.initCommand(CommandType.CALCEXPR);
        ManagerCommand invoker = new ManagerCommand(command);
        invoker.invokeCommand();

        System.out.println(receiver.getResult());


    }
}
