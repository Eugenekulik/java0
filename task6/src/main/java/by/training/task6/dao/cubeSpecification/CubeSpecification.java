package by.training.task6.dao.cubeSpecification;

import by.training.task6.bean.Cube;
import by.training.task6.dao.Specification;

public interface CubeSpecification extends Specification<Cube> {
    boolean specified(Cube cube);
}
