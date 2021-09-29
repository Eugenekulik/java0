package by.training.task6.dao.cubecomparator;

import by.training.task6.bean.Cube;
import by.training.task6.service.CalculateCubeArea;
import by.training.task6.service.ServiceFactory;

import java.util.Comparator;

public class CubeComparatorByArea implements Comparator<Cube> {
    @Override
    public int compare(Cube cube1, Cube cube2) {
        CalculateCubeArea calculateCubeArea = ServiceFactory.getInstance().getCalculateCubeArea();
        if(calculateCubeArea.applyAsDouble(cube1) < calculateCubeArea.applyAsDouble(cube2)) {
            return -1;
        }
        if(calculateCubeArea.applyAsDouble(cube1) > calculateCubeArea.applyAsDouble(cube2)) {
            return 1;
        }
        return 0;
    }
}
