package by.training.task2.controller;

import by.training.task2.beans.TaskInfo;
import by.training.task2.command.*;

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
        TaskInfo information = new TaskInfo();
        CurrentData currentData = new CurrentData();
        boolean isWork=true;
        Locale locale = new Locale("ru","RU");
        Messanger messanger = new Messanger();
        while (isWork) {
            messanger.print("Choose Language(1 - russian, 2 - english): ");
            String lang = reader.getString("");
            if (lang == "1") {
                locale = new Locale("ru", "RU");
                break;
            }
            if (lang == "2") {
                locale = new Locale("en", "GB");
                break;
            }
            if (lang == "exit") {
                isWork=false;
                break;
            }
            messanger.print("write current data");
        }
        isWork=true;
        while(isWork) {

            String l=reader.getString(information.getInfo(locale));
            String []line = l.split(" ");
            String cmd = line[0];
            if(cmd=="exit"){
                isWork=false;
                break;
            }
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
