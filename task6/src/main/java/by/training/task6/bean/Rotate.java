package by.training.task6.bean;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

public class Rotate {
    private double x;
    private double y;
    private double z;
    private final DoubleUnaryOperator checkRotate = anyRotate ->
            anyRotate >= 0 && anyRotate <= Math.PI ? anyRotate
                : Math.abs(anyRotate % Math.PI);


    public Rotate(double newX, double newY, double newZ) {
        x = checkRotate.applyAsDouble(newX);
        y = checkRotate.applyAsDouble(newY);
        z = checkRotate.applyAsDouble(newZ);
    }

    public double getX() {
        return x;
    }

    public void setX(double newX) {
        x = checkRotate.applyAsDouble(newX);
    }

    public double getY() {
        return y;
    }

    public void setY(double newY) {
        y = checkRotate.applyAsDouble(newY);
    }

    public double getZ() {
        return z;
    }

    public void setZ(double newZ) {
        z = checkRotate.applyAsDouble(newZ);
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
