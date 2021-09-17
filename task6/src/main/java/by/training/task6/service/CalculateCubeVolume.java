package by.training.task6.service;

import by.training.task6.bean.Cube;

/**
 * This class calculate cube volume.
 */
public class CalculateCubeVolume {
    /**
     * This method calculates the volume of the passed cube.
     * @param cube
     * @return double value of the cube volume.
     */
    public double execute(Cube cube){
        return Math.pow(cube.getEdge(),3);
    }
}
