package by.training.task6.dao.cubeComparator;

import by.training.task6.bean.Cube;
import by.training.task6.service.CalculateCubeVolume;
import by.training.task6.service.ServiceFactory;

import java.util.Comparator;

public class CubeComparatorByVolume implements Comparator<Cube> {
    @Override
    public int compare(Cube cube1, Cube cube2) {
        CalculateCubeVolume calculateCubeVolume = ServiceFactory.getInstance().getCalculateCubeVolume();
        if(calculateCubeVolume.apply(cube1) < calculateCubeVolume.apply(cube2)) {
            return -1;
        }
        if(calculateCubeVolume.apply(cube1) > calculateCubeVolume.apply(cube2)) {
            return 1;
        }
        return 0;
    }
}
