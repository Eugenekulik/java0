package by.training.task6.service.cubePlaneRatio;

import by.training.task6.bean.Cube;

import java.util.function.Function;

public class CubePlaneRatioXOZ implements Function<Cube,Double> {
    public Double apply(Cube cube){
        if(cube.getRotate().getX() == 0 || cube.getRotate().getZ() == 0){
            if(cube.getCoordinate().getY()>=0){
                return 0.0;
            }
            return -1/(cube.getCoordinate().getY()/cube.getEdge());
        }
        else if(cube.getRotate().getX() == Math.PI || cube.getRotate().getZ() == Math.PI){
            if(cube.getCoordinate().getY()<=0){
                return 0.0;
            }
            return 1/(cube.getCoordinate().getY()/cube.getEdge());
        }
        return 0.0;
    }
}
