package by.training.demoThread.ex5aThreadThreadToDisable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main{
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        logger.info("Главный поток начал работу...");
        ThreadToDisable myThread = new ThreadToDisable();
        Thread myT = new Thread(myThread, "name of ThreadToDisable");
        myT.start();
        try{
            Thread.sleep(1100);
            myThread.disable();
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            logger.info("Поток прерван");
        }
        logger.info("Главный поток завершил работу...");
    }
}

