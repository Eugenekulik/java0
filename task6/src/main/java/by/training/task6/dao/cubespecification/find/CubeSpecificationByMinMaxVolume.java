package by.training.task6.dao.cubespecification.find;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMinMaxVolume implements CubeFindSpecification {
    private final double min;
    private final double max;

    public CubeSpecificationByMinMaxVolume(double min, double max) {
        this.min = min;
        this.max = max;
    }


    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeVolume().apply(cube) >= min
                && ServiceFactory.getInstance().getCalculateCubeVolume().apply(cube) <= max;
    }
}
