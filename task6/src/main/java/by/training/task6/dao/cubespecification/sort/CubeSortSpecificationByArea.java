package by.training.task6.dao.cubespecification.sort;

import by.training.task6.bean.Cube;
import by.training.task6.dao.cubecomparator.CubeComparatorFactory;

public class CubeSortSpecificationByArea implements CubeSortSpecification{
    @Override
    public int specified(Cube cube1, Cube cube2) {
        return CubeComparatorFactory.getInstance().getCubeComparatorByArea().compare(cube1,cube2);
    }
}
