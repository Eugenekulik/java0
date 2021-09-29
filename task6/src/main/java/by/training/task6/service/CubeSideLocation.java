package by.training.task6.service;

import by.training.task6.bean.Cube;

/**
 * This class checks some conditions.
 */
public class CubeSideLocation {
    /**
     * This method checks if one of the cube's sides lies on the coordinate plane.
     * @param cube Cube
     * @return boolean, true if lies
     */
    public boolean sideOnCoordinatePlane(Cube cube) {
        if (!checkRotate(cube)) {
            return false;
        }
        return cube.getCoordinate().getX() == 0
                || cube.getCoordinate().getY() == 0
                || cube.getCoordinate().getZ() == 0
                || cube.getCoordinate().getX() + cube.getEdge() == 0
                || cube.getCoordinate().getY() + cube.getEdge() == 0
                || cube.getCoordinate().getZ() + cube.getEdge() == 0;
    }

    /**
     * This method checks if the cube is parallel to one of the coordinate planes.
     * @param cube Cube
     * @return boolean, true if parallel
     */
    public boolean checkRotate(Cube cube) {
        int check=0;
        if (cube.getRotate().getX() != 0 || cube.getRotate().getX()!=Math.PI/2) {
            check++;
        }
        if (cube.getRotate().getY() != 0 || cube.getRotate().getY()!=Math.PI/2) {
            check++;
        }
        if (cube.getRotate().getZ() != 0 || cube.getRotate().getZ()!=Math.PI/2) {
            check++;
        }
        return check<2;
    }
}
