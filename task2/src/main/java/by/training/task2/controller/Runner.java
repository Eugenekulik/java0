package by.training.task2.controller;

import by.training.task2.beans.TaskInfo;
import by.training.task2.controller.command.*;

import by.training.task2.service.ServiceException;
import by.training.task2.view.Messanger;
import by.training.task2.view.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);
    private Runner(){};

    /**
     * Running method of the application
     * @param args
     */
    public static void main(String[] args) {
        logger.info("Program Start");
        Reader reader = new Reader();
        TaskInfo information = new TaskInfo();
        CurrentData currentData = new CurrentData();
        boolean isWork=true;
        Messanger messanger = new Messanger();
        messanger.print("Choose Language(1 - russian, 2 - english): ");
        String lang = reader.getString("");
        Locale locale;
        if (lang.equals("1")) {
            locale = new Locale("ru", "RU");
            logger.info("Locale: ru");
        }
        else if (lang.equals("2")) {
            locale = new Locale("en", "GB");
            logger.info("Locale: en");
        }
        else {
            locale = new Locale("ru","RU");
            logger.info("Locale: ru");
        }
        messanger.print(information.getInfo(locale));
        while(isWork) {
            logger.info("main loop");
            String l=reader.getString("");
            String []line = l.split(" ");
            String cmd = line[0];
            String[] argsProg=new String[line.length-1];
            for(int i=0;i< line.length-1;i++){
                argsProg[i]=line[i+1];
            }
            if(cmd.equals("exit")){
                logger.info("end work");
                break;
            }
            if(cmd.equals("info")){
                messanger.print(information.getInfo(locale));
                continue;
            }

            if(!currentData.check(CommandType.valueOf(cmd), argsProg)){
                messanger.print("write current variables");
                continue;
            }
            Receiver receiver = new Receiver(argsProg);
            Client client = new Client(receiver);
            Command command = client.initCommand(CommandType.valueOf(cmd));
            ManagerCommand managerCommand = new ManagerCommand(command);
            try{
                managerCommand.invokeCommand();
                messanger.print(receiver.getResult());
                logger.info(CommandType.valueOf(cmd) + " is done");
            }
            catch (ServiceException e){
                logger.warn(e.getMessage());
                messanger.print(e.getMessage()+"\ntry again\n");
            }
        }


    }
}
