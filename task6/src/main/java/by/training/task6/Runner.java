package by.training.task6;

import by.training.task6.bean.Cube;
import by.training.task6.bean.CubeException;

public class Runner {
    private Runner(){};
    public static void main(String []argv){
        try {
            Cube cube  = new Cube(10,0,0,0);
        } catch (CubeException e) {
            e.printStackTrace();
        }
    }
}
