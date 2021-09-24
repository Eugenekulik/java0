package by.training.task6.service;

import by.training.task6.bean.Cube;

import java.util.function.Function;

/**
 * This class calculate cube area.
 * Implement Functional Interface Function<Cube,Double>
 */
public class CalculateCubeArea implements Function<Cube, Double> {
    /**
     * This method calculates the area of the passed cube.
     *
     * @param cube Cube
     * @return double value of the cube area
     */
    public Double apply(Cube cube) {
        return 6 * cube.getEdge() * cube.getEdge();
    }
}
