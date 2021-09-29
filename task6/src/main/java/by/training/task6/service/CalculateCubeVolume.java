package by.training.task6.service;

import by.training.task6.bean.Cube;
import java.util.function.ToDoubleFunction;

/**
 * This class calculate cube volume.
 */
public class CalculateCubeVolume implements ToDoubleFunction<Cube> {
    /**
     * This method calculates the volume of the passed cube.
     *
     * @param cube
     * @return double value of the cube volume.
     */
    @Override
    public double applyAsDouble(Cube cube) {
        return Math.pow(cube.getEdge(), 3);
    }
}
