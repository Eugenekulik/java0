package by.training.task1;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Runner {
    private static final Logger logger=LogManager.getLogger(Runner.class);
    private Runner(){};
    public static void main(String args[])
    {
        logger.debug("start application");
        System.out.println("Hello, World");
        logger.warn("goodbye");
    }
}
