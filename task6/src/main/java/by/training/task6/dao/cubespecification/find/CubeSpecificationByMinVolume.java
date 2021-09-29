package by.training.task6.dao.cubespecification.find;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMinVolume implements CubeFindSpecification {
    private final double min;

    public CubeSpecificationByMinVolume(double min) {
        this.min = min;
    }

    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeVolume().apply(cube) >= min;
    }

}
