package by.training.task6.dao.cubespecification.find;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubespecification.CubeSpecification;

public interface CubeFindSpecification extends CubeSpecification {
    boolean specified(Cube cube);
}
