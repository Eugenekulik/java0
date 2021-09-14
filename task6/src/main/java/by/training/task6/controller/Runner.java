package by.training.task6.controller;

import by.training.task6.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);
    public static void main(String []args){
        logger.info("Start program");

        logger.info("End program");
    }
}
