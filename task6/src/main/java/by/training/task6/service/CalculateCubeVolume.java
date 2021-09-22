package by.training.task6.service;

import by.training.task6.bean.Cube;

import java.util.function.Function;

/**
 * This class calculate cube volume.
 */
public class CalculateCubeVolume implements Function<Cube, Double> {
    /**
     * This method calculates the volume of the passed cube.
     * @param cube
     * @return double value of the cube volume.
     */
    public Double apply(Cube cube){
        return Math.pow(cube.getEdge(),3);
    }
}
