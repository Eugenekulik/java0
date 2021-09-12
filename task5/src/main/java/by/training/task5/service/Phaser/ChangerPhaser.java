package by.training.task5.service.Phaser;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.controller.Runner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Lock;

public class ChangerPhaser implements Runnable{
    private static final Logger logger = LogManager.getLogger(Runner.class);
    private Iterate actual;
    Matrix matrix;
    Phaser phaser;
    Lock locker;
    int value;
    public ChangerPhaser(Matrix matrix,Phaser phaser,Lock locker,int value,Iterate iterate){
        this.phaser = phaser;
        phaser.register();
        this.locker = locker;
        this.matrix = matrix;
        this.value = value;
        this.actual = iterate;
    }
    @Override
    public void run() {
        logger.info(Thread.currentThread() + " start");
        while(true){
            locker.lock();
            if (actual.getActual() >= matrix.getVertical()) {
                locker.unlock();
                break;
            }
            int iterate = actual.getActual();
            actual.plus();
            locker.unlock();
            matrix.set(iterate, iterate, value);
            logger.info(Thread.currentThread() + " change iterate = " + iterate);
            logger.info(Thread.currentThread() +" " + phaser.arrive());
        }
        phaser.arriveAndDeregister();
        logger.info(Thread.currentThread() + "end work");
    }
}
