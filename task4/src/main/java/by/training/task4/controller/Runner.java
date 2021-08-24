package by.training.task4.controller;


import by.training.task4.bean.*;
import by.training.task4.controller.command.Client;
import by.training.task4.controller.command.Command;
import by.training.task4.controller.command.ManagerCommand;
import by.training.task4.service.ServiceException;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class Runner {
    public static final Logger logger = LogManager.getLogger(Runner.class);
    private Runner(){}
    public static void main(String []args){
        logger.info("Start program");
        ViewFactory viewFactory = ViewFactory.getInstance();
        Reader reader = viewFactory.getReader();
        Messenger messenger =viewFactory.getMessenger();
        messenger.print("Choose language: 1 - Russian,2 - English");
        String lang = reader.getString();
        if(lang.equals("1")){
            Locale locale = new Locale("ru","RU");
            messenger.initBundle("text",locale);
        }
        logger.info("Main loop");
        while(RunTimeInfo.isWork()){
            messenger.printProperty("simple.write_command");
            String cmd = reader.getString();
            CommandData commandData = new CommandData();
            Client client = new Client(commandData);
            Command command=null;
            try {
                command = client.initCommand(CommandType.valueOf(cmd));
            }
            catch (Exception e){
                messenger.printProperty("simple.try_again");
            }
            ManagerCommand managerCommand = new ManagerCommand(command);
            try{
                managerCommand.invokeCommand();
            }
            catch (ServiceException e){
                messenger.printProperty(e.getMessage());
                logger.warn(e.getMessage());
            }
        }

        logger.info("End program");
    }
}
