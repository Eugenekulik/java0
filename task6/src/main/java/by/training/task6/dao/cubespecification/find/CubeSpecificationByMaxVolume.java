package by.training.task6.dao.cubespecification.find;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMaxVolume implements CubeFindSpecification {
    private final double max;

    public CubeSpecificationByMaxVolume(double max) {
        this.max = max;
    }

    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeVolume().apply(cube) <= max;
    }
}