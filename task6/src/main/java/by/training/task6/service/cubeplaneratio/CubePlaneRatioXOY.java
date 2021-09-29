package by.training.task6.service.cubeplaneratio;

import by.training.task6.bean.Cube;

import java.util.function.ToDoubleFunction;

public class CubePlaneRatioXOY implements ToDoubleFunction<Cube> {
    @Override
    public double applyAsDouble(Cube cube) {
        if (cube.getRotate().getX() == 0 || cube.getRotate().getY() == 0) {
            if (cube.getCoordinate().getZ() >= 0) {
                return 0.0;
            }
            return -1 / (cube.getCoordinate().getZ() / cube.getEdge());
        } else if (cube.getRotate().getX() == Math.PI || cube.getRotate().getY() == Math.PI) {
            if (cube.getCoordinate().getZ() <= 0) {
                return 0.0;
            }
            return 1 / (cube.getCoordinate().getZ() / cube.getEdge());
        }
        return 0.0;
    }
}
