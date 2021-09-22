package by.training.task6.dao.cubeSpecification;

import by.training.task6.bean.Cube;
import by.training.task6.service.ServiceFactory;

public class CubeSpecificationByMaxArea implements CubeSpecification{
    private double max;
    public CubeSpecificationByMaxArea(double max){
        this.max = max;
    }
    @Override
    public boolean specified(Cube cube) {
        return ServiceFactory.getInstance().getCalculateCubeArea().apply(cube) <= max;
    }
}
