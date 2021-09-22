package by.training.task6.bean;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Rotate {
    private double x;
    private double y;
    private double z;
    public Rotate(double newX,double newY,double newZ){
        x = checkRotate.apply(newX);
        y = checkRotate.apply(newY);
        z = checkRotate.apply(newZ);
    }
    public double getX() {
        return x;
    }

    public void setX(double newX) {
        x = checkRotate.apply(newX);
    }

    public double getY() {
        return y;
    }

    public void setY(double newY) {
        y = checkRotate.apply(newY);
    }

    public double getZ() {
        return z;
    }

    public void setZ(double newZ) {
        z = checkRotate.apply(newZ);
    }
    private UnaryOperator<Double> checkRotate = (anyRotate)->{
        return anyRotate >= 0 && anyRotate <= Math.PI? anyRotate
                : Math.abs(anyRotate % Math.PI);
    };
}
