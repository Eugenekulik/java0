package by.training.task6.service.cubeplaneratio;

import by.training.task6.bean.Cube;

import java.util.function.ToDoubleFunction;

public class CubePlaneRatioYOZ implements ToDoubleFunction<Cube> {
    @Override
    public double applyAsDouble(Cube cube) {
        if (cube.getRotate().getZ() == 0 || cube.getRotate().getY() == 0) {
            if (cube.getCoordinate().getX() >= 0) {
                return 0.0;
            }
            return -1 / (cube.getCoordinate().getX() / cube.getEdge());
        } else if (cube.getRotate().getZ() == Math.PI || cube.getRotate().getY() == Math.PI) {
            if (cube.getCoordinate().getX() <= 0) {
                return 0.0;
            }
            return 1 / (cube.getCoordinate().getX() / cube.getEdge());
        }
        return 0.0;
    }
}
