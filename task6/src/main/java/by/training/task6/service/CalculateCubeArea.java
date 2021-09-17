package by.training.task6.service;

import by.training.task6.bean.Cube;

/**
 * This class calculate cube area.
 */
public class CalculateCubeArea {
    /**
     * This method calculates the area of the passed cube.
     * @param cube Cube
     * @return double value of the cube area
     */
    public double execute(Cube cube){
        return 6 * cube.getEdge()*cube.getEdge();
    }
}
