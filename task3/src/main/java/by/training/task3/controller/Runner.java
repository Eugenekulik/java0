package by.training.task3.controller;


import by.training.task3.bean.*;
import by.training.task3.controller.command.Client;
import by.training.task3.controller.command.Command;
import by.training.task3.controller.command.ManagerCommand;
import by.training.task3.dao.DaoFactory;
import by.training.task3.service.GetArrayFromFile;
import by.training.task3.service.GetMatrixFromFile;
import by.training.task3.service.ServiceException;
import by.training.task3.service.arraySort.BubbleSort;
import by.training.task3.service.arraySort.HashTableSort;
import by.training.task3.service.arraySort.ShekerSort;
import by.training.task3.service.arraySort.SimpleChoiceSort;
import by.training.task3.view.Messenger;
import by.training.task3.view.Reader;
import by.training.task3.view.ViewFactory;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Runner {
    public static void main(String []args) {
       Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
       while(RuntimeInformation.getIsWork()){
            messenger.print("Write command");
            String cmd = reader.getString();
            CommandData commandData = new CommandData();
            Client client = new Client(commandData);
            Command command = client.initCommand(CommandType.valueOf(cmd));
            ManagerCommand managerCommand = new ManagerCommand(command);
            try{
                managerCommand.invokeCommand();
            }
            catch (ServiceException e){
                messenger.print(e.getMessage());
            }
        }
    }
}
