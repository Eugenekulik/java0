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

public final class Runner {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    private Runner() {
        super();
    }
    /**
     * Main method of program.
     * @param args External parameters
     */
    public static void main(final String[] args) {
        LOGGER.info("Program start");
        try {
            Matrix m =
                    new MatrixCreator("matrix.txt").create();
            ThreadGetter threadGetter =
                    new ThreadGetter("threads.txt");
            Client client = new Client(m, threadGetter.get());
            client.initCommand(CommandType.valueOf("MATRIXCHANGE"));
            ManagerCommand managerCommand = new ManagerCommand(client.
                    initCommand(CommandType.valueOf("MATRIXCHANGE")));
            managerCommand.invokeCommand();
            ViewFactory.getInstance().getMessenger()
                    .print(m.toString());
            LOGGER.info("End program");
        } catch (ServiceException e) {
            ViewFactory.getInstance().getMessenger().print(e.getMessage());
        }

    }
}
