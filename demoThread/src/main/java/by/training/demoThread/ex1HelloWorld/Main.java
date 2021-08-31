package by.training.demoThread.ex1HelloWorld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        logger.info("Start programm");
        MyThread myThread = new MyThread();
        myThread.setName("Hello world thread");
        myThread.start();
        logger.info("main complete");
    }
}
