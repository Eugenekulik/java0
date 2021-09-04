package by.training.demoThread.threadPriority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger  =LogManager.getLogger(Main.class);

    public static void main(String[] args){
        logger.info("Start program");
        PriorityThread min = new PriorityThread("MIN");
        PriorityThread max = new PriorityThread("MAX");
        PriorityThread norm = new PriorityThread("NORM");
        min.setPriority(Thread.MIN_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        min.start();
        norm.start();
        max.start();
        logger.info("End programm");
    }
}
