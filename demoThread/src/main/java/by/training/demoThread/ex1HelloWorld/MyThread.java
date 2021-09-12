package by.training.demoThread.ex1HelloWorld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread extends Thread{
    private static final Logger logger = LogManager.getLogger(MyThread.class);
    public void run(){
        logger.info("start");
        System.out.println("Hello, world!");
        logger.info("MyThread complete");
    }
}
