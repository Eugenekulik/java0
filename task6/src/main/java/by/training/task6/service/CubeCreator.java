package by.training.task6.service;

import by.training.task6.bean.Cube;
import by.training.task6.bean.CubeException;
import by.training.task6.dao.DaoException;
import by.training.task6.dao.DaoFactory;
import by.training.task6.dao.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CubeCreator {
    private static final Logger logger = LogManager.getLogger(CubeCreator.class);
    private FileReader fileReader;
    /**
     * Default method create which create
     * Cube with side 1 and initial point (0,0,0).
     *
     * @return new Cube
     */
    public Cube create() throws ServiceException {
        try {
            return new Cube(1, 0, 0, 0);
        } catch (CubeException e) {
            logger.warn(e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * This method create Cube by using double value
     * of cube side and double values of initial coordinate.
     *
     * @return new Cube
     */
    public Cube create(double cubeSide, double... values) throws ServiceException {
        if (values.length != 3) {
            logger.warn("wrong number of arguments.");
            throw new ServiceException("wrong number of arguments.");
        }
        try {
            return new Cube(cubeSide, values);
        } catch (CubeException e) {
            logger.warn(e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * this method initializes the file
     * to create cubes based on the file data.
     * @param file String file path
     * @throws ServiceException exception for Service layer.
     */
    public void initFile(String file) throws ServiceException {
        try {
            fileReader = DaoFactory.getInstance().getFileReader(file);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * This method read data from a previously initialized file and create cube;
     * @return Cube
     * @throws ServiceException
     */
    public Cube createFromFile() throws ServiceException {
        try {
            double[] data = new Parcer().parse(fileReader.readNextLine());
            if(data.length != 3){
                throw new ServiceException("too little or too much arguments to create Cube");
            }
            return new Cube(data[0],data[1],data[2],data[3]);
        } catch (CubeException|ServiceException e){
            logger.warn(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
