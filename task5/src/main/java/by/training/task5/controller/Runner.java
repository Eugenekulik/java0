package by.training.task5.controller;

import by.training.task5.bean.Matrix;
import by.training.task5.controller.command.Client;
import by.training.task5.controller.command.CommandType;
import by.training.task5.controller.command.ManagerCommand;
import by.training.task5.service.MatrixCreator;
import by.training.task5.service.ServiceException;
import by.training.task5.service.ThreadGetter;
import by.training.task5.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String []args){
        logger.info("Program start");
        try {
            Matrix m = new MatrixCreator("src/main/resources/matrix.txt").create();
            ThreadGetter threadGetter = new ThreadGetter("src/main/resources/threads.txt");
            Client client = new Client(m, threadGetter.get());
            client.initCommand(CommandType.valueOf("MATRIXCHANGE"));
            ManagerCommand managerCommand = new ManagerCommand(client.
                    initCommand(CommandType.valueOf("MATRIXCHANGE")));
            managerCommand.invokeCommand();
            ViewFactory.getInstance().getMessanger()
                    .print(m.toString());
            logger.info("End program");
        }
        catch (ServiceException e){
            ViewFactory.getInstance().getMessanger().print(e.getMessage());
        }

    }
}
