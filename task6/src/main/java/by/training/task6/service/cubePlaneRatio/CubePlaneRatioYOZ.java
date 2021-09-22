package by.training.task6.service.cubePlaneRatio;

import by.training.task6.bean.Cube;

import java.util.function.Function;

public class CubePlaneRatioYOZ implements Function<Cube, Double> {
    public Double apply(Cube cube){
        if(cube.getRotate().getZ() == 0 || cube.getRotate().getY() == 0){
            if(cube.getCoordinate().getX()>=0){
                return 0.0;
            }
            return -1/(cube.getCoordinate().getX()/cube.getEdge());
        }
        else if(cube.getRotate().getZ() == Math.PI || cube.getRotate().getY() == Math.PI){
            if(cube.getCoordinate().getX()<=0){
                return 0.0;
            }
            return 1/(cube.getCoordinate().getX()/cube.getEdge());
        }
        return 0.0;
    }
}
