package by.training.task6.dao.cubeComparator;

import by.training.task6.bean.Cube;
import by.training.task6.service.CalculateCubeArea;
import by.training.task6.service.CalculateCubeVolume;
import by.training.task6.service.ServiceFactory;

import java.util.Comparator;

public class CubeComparatorByArea implements Comparator<Cube> {
    @Override
    public int compare(Cube cube1, Cube cube2) {
        CalculateCubeArea calculateCubeArea = ServiceFactory.getInstance().getCalculateCubeArea();
        if(calculateCubeArea.apply(cube1) < calculateCubeArea.apply(cube2)) {
            return -1;
        }
        if(calculateCubeArea.apply(cube1) > calculateCubeArea.apply(cube2)) {
            return 1;
        }
        return 0;
    }
}
