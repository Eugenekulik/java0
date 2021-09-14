package by.training.task6.service;

import by.training.task6.bean.Cube;

public class CubeCreator {

    public Cube create(){
        return new Cube(0,0,0,1,1,1);
    }
    public Cube create(double... values) throws ServiceException {
        if(values.length != 6){
            throw new ServiceException("wrong number of arguments");
        }
        return new Cube(values);
    }
}
