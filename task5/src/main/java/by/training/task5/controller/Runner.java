package by.training.task5.controller;

import by.training.task5.bean.Matrix;
import by.training.task5.service.Phaser.MatrixChangePhaser;
import by.training.task5.service.ReentrantLock.MatrixChangeLock;
import by.training.task5.service.MatrixCreator;
import by.training.task5.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String []args){
        logger.info("Program start");
        try {
            Matrix m = new MatrixCreator("src/main/resources/matrix.txt").create();
            int[] values = {4,7,2,75,7,3,8,3,79,5};
            MatrixChangePhaser matrixChangePhaser = new MatrixChangePhaser(m,3,values);
            matrixChangePhaser.change();
            System.out.println(m.toString());
            logger.info("End program");
        }
        catch (ServiceException e){
            System.out.println(e.getMessage());
        }

    }
}
