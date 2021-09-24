package by.training.task6.service;

import by.training.task6.bean.Cube;
import by.training.task6.bean.CubeException;

public class CubeCreator {
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
            throw new ServiceException("wrong number of arguments");
        }
        try {
            return new Cube(cubeSide, values);
        } catch (CubeException e) {
            throw new ServiceException(e);
        }
    }
}
