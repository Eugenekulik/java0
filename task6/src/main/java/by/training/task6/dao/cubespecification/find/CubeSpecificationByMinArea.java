package by.training.task6.dao.cubespecification.find;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMinArea implements CubeFindSpecification {
    private final double min;

    public CubeSpecificationByMinArea(double min) {
        this.min = min;
    }

    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeArea().apply(cube) >= min;
    }
}
