package by.training.task1.runner;

import by.training.task1.service.Menu;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    private Runner() {
    }

    public static void main(String[] args) {
        logger.info("Program start");
        Menu m = new Menu();
        m.menurun();
    }
}
