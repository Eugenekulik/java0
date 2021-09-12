package by.training.demoThread.ex6Synchronized;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class CountThread implements Runnable{
    private static final Logger logger = LogManager.getLogger(Main.class);

    CommonResource res;

    CountThread(CommonResource res){
        this.res = res;
    }
    public void run(){
        synchronized (res) {
            res.x = 1;

            for (int i = 1; i < 5; i++) {
                logger.info(Thread.currentThread().getName() + " " + res.x);
                res.x++;
            }
        }
    }
}
