package by.training.task6.bean;

import java.util.Objects;
import java.util.function.UnaryOperator;

public class Rotate {
    private double x;
    private double y;
    private double z;
    private final UnaryOperator<Double> checkRotate = (anyRotate) -> {
        return anyRotate >= 0 && anyRotate <= Math.PI ? anyRotate
                : Math.abs(anyRotate % Math.PI);
    };

    public Rotate(double newX, double newY, double newZ) {
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rotate rotate = (Rotate) o;
        return Double.compare(rotate.x, x) == 0 && Double.compare(rotate.y, y) == 0 && Double.compare(rotate.z, z) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
