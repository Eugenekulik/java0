package by.training.task6.dao.cubespecification.sort;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubespecification.CubeSpecification;

public interface CubeSortSpecification extends CubeSpecification {
    int specified(Cube cube1,Cube cube2);
}
