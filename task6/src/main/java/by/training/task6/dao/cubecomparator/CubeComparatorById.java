package by.training.task6.dao.cubecomparator;

import by.training.task6.bean.Cube;

import java.util.Comparator;

public class CubeComparatorById implements Comparator<Cube> {
    @Override
    public int compare(Cube o1, Cube o2) {
        if(o1.getId() > o2.getId()){
            return 1;
        }
        else {
            return -1;
        }
    }
}
