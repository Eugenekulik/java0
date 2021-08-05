package by.training.task2.controller;

import by.training.task2.beans.TaskInfo;
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
        Reader reader = new Reader();
        Messanger messanger = new Messanger("ru","RU");
        messanger.print("");
        TaskInfo information = new TaskInfo();
        CurrentData currentData= new CurrentData();
        while(true) {
            String l=reader.getString("");
            String []line = l.split(" ");
            String cmd = line[0];
            String[] argsProg=new String[line.length-1];
            for(int i=0;i< line.length-1;i++){
                argsProg[i]=line[i+1];
            }
            if(cmd=="exit"){
                break;
            }
            currentData.exam(CommandType.valueOf(cmd),argsProg);
            Receiver receiver =new Receiver(argsProg);
            Client client= new Client(receiver);
            Command command = client.initCommand(CommandType.valueOf(cmd));
            ManagerCommand managerCommand = new ManagerCommand(command);
            managerCommand.invokeCommand();
            messanger.print(receiver.getResult());
        }


    }
}
