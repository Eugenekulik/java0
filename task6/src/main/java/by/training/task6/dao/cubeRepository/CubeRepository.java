package by.training.task6.dao.cubeRepository;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubeSpecification.CubeSpecification;

import java.util.List;
import java.util.stream.Stream;

public interface CubeRepository {
    void addCube(Cube cube);

    void removeCube(Cube cube);

    List quary(CubeSpecification cubeSpecification);

}
