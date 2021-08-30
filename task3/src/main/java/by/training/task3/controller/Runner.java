package by.training.task3.controller;


import by.training.task3.bean.CommandData;
import by.training.task3.controller.command.CommandType;
import by.training.task3.bean.RuntimeInformation;
import by.training.task3.controller.command.Client;
import by.training.task3.controller.command.Command;
import by.training.task3.controller.command.ManagerCommand;
import by.training.task3.service.ServiceException;
import by.training.task3.view.Messenger;
import by.training.task3.view.Reader;
import by.training.task3.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;


public class Runner {
    public static final Logger logger = LogManager.getLogger(Runner.class);

    private Runner(){}

    public static void main(String []args) {
        logger.info("Start program");
        Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        messenger.print("Choice language: 1 - Russian, 2 - English:");
        String lang = reader.getString();
        if(lang=="1"){
            Locale locale = new Locale("ru","RU");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("text",locale);
            messenger.resourceBundleInit(resourceBundle);
        }
        else if(lang=="2"){
            Locale locale = new Locale("en","US");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("text",locale);
            messenger.resourceBundleInit(resourceBundle);
        }
        else{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("text");
            messenger.resourceBundleInit(resourceBundle);
        }
       while(RuntimeInformation.getIsWork()){
           logger.info("Main loop");
            messenger.printProperty("simple.write_command");
            String cmd = reader.getString();
            CommandData commandData = new CommandData();
            Client client = new Client(commandData);
            Command command;
            try {
                command = client.initCommand(CommandType.valueOf(cmd));
            }
            catch (IllegalArgumentException e){
                messenger.print(e.getMessage());
                messenger.printProperty("simple.try_again");
                continue;
            }
            ManagerCommand managerCommand = new ManagerCommand(command);
            try{
                managerCommand.invokeCommand();
                logger.info("command execute correctly");
            }
            catch (ServiceException e){
                messenger.printProperty(e.getMessage());
                logger.info(e.getMessage());
                messenger.printProperty("simple.try_again");
            }
        }
    }
}
