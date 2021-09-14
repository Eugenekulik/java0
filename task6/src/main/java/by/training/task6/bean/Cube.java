package by.training.task6.bean;

public class Cube {

    private Point []points;

    public Cube(double... args){
        points = new Point[2];
        points[0] = new Point(args[0],
                              args[1],
                              args[2]);
        points[1] = new Point(args[3],
                              args[4],
                              args[5]);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private class Point{
        private double x;
        private double y;
        private double z;
        public Point(double newX,double newY,double newZ){
            x = newX;
            y = newY;
            z = newZ;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
