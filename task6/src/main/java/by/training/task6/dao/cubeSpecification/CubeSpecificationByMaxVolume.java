package by.training.task6.dao.cubeSpecification;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMaxVolume implements CubeSpecification {
    private final double max;

    public CubeSpecificationByMaxVolume(double max) {
        this.max = max;
    }

    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeVolume().apply(cube) <= max;
    }
}