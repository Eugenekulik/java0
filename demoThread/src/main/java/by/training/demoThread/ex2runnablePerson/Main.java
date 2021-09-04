package by.training.demoThread.ex2runnablePerson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(by.training.demoThread.helloWorld.Main.class);

    public static void main(String[] args){
        logger.info("Start programm");
        RunnablePerson bob = new RunnablePerson("Bob");
        RunnablePerson alice = new RunnablePerson("Alice");
        RunnablePerson margo = new RunnablePerson("Margo");
        Thread thread1 = new Thread(bob);
        Thread thread2 = new Thread(alice);
        Thread thread3 = new Thread(margo);
        thread1.start();
        thread2.start();
        thread3.start();
        try{
            thread1.join();
            thread2.join();
            thread3.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("main complete");
    }
}
