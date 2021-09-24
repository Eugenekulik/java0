package by.training.task6.dao.cubeSpecification;

import by.training.task6.bean.Cube;

public class CubeSpecificationById implements CubeSpecification{
    private int id;
    public CubeSpecificationById(int id){
        this.id = id;
    }
    @Override
    public boolean specified(Cube cube) {
        return cube.getId()==id;
    }
}
