package by.training.task6.dao.cubeRepository;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubeSpecification.CubeSpecification;

import java.util.Collection;
import java.util.List;

public interface CubeRepository {
    void addCube(Cube cube);
    Collection<Cube> getAll();
    void removeCube(int id);

    List quary(CubeSpecification cubeSpecification);

}
