package by.training.task6.dao.cubeSpecification;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMinMaxVolume implements CubeSpecification {
    private double min;
    private double max;
    public CubeSpecificationByMinMaxVolume(double min, double max){
        this.min = min;
        this.max = max;
    }
    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeVolume().apply(cube) >= min
                && ServiceFactory.getInstance().getCalculateCubeVolume().apply(cube) <= max;
    }
}
