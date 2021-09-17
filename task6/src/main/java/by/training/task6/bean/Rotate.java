package by.training.task6.bean;

public class Rotate {
    private double x;
    private double y;
    private double z;
    public Rotate(double newX,double newY,double newZ){
        x = newX;
        y = newY;
        z = newZ;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
