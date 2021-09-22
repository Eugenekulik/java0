package by.training.task6.dao.cubeSpecification;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMinMaxArea implements CubeSpecification{
    private double min;
    private double max;
    public CubeSpecificationByMinMaxArea(double min, double max){
        this.min = min;
        this.max = max;
    }
    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeArea().apply(cube) >= min
                && ServiceFactory.getInstance().getCalculateCubeArea().apply(cube) >= max;
    }
}
