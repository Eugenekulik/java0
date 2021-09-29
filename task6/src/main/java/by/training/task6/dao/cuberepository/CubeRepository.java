package by.training.task6.dao.cuberepository;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubespecification.CubeSpecification;

import java.util.List;

public interface CubeRepository {
    void addCube(Cube cube);

    void removeCube(Cube cube);

    List<Cube> quary(CubeSpecification cubeSpecification);

}
