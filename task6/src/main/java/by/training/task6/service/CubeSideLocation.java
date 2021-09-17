package by.training.task6.service;

import by.training.task6.bean.Cube;

/**
 * This class check that any
 */
public class CubeSideLocation {
    public boolean sideOnCoordinatePlane(Cube cube){
        if(cube.getRotate().getX() != 0
                || cube.getRotate().getY() != 0
                || cube.getRotate().getZ() != 0){
                    return false;
        }
        if(cube.getCoordinate().getX() == 0
           || cube.getCoordinate().getY() == 0
           || cube.getCoordinate().getZ() + cube.getEdge() == 0
           || cube.getCoordinate().getX() + cube.getEdge() == 0
           || cube.getCoordinate().getX() + cube.getEdge() == 0
           || cube.getCoordinate().getX() + cube.getEdge() == 0) {
            return true;
        }
        return false;
    }
}
