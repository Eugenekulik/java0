package by.training.task6.bean;

import java.util.Objects;

/**
 * Cube is information expert class which realize entity cube.
 */
public class Cube {
    /**
     * Rotation in x,y,z.
     */
    private Rotate rotate;

    private int id;

    private double edge;
    /**
     * Array of Points with which to built a cube.
     */
    private Point coordinate;

    /**
     * Constructor which takes vararg of double
     * and create two points forming a cube.
     *
     * @param cubeEdge        double value of cube side
     * @param pointCoordinate array with coordinate initial point
     */
    public Cube(double cubeEdge, double... pointCoordinate) throws CubeException {
        if (pointCoordinate.length != 3) {
            throw new CubeException("too much or too many argument for coordinate.");
        }
        if (cubeEdge <= 0) {
            throw new CubeException("side can't be 0 or negative.");
        }
        edge = cubeEdge;
        coordinate = new Point(pointCoordinate[0],pointCoordinate[1],pointCoordinate[2]);
        rotate = new Rotate(0, 0, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    /**
     * Getter for cube side.
     *
     * @return double side
     */
    public double getEdge() {
        return edge;
    }

    /**
     * Setter for cube side.
     *
     * @param edge new double value
     */
    public void setEdge(double edge) {
        this.edge = edge;
    }

    /**
     * This method return one of two points which forming Cube.
     *
     * @return Point
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * This method changes one of two points which forming Cube.
     */
    public void setCoordinate(int... args) throws CubeException {
        if (args.length != 3) {
            throw new CubeException("Too much or too little argument for Point.");
        }
        coordinate = new Point(args[0], args[1], args[2]);
    }

    /**
     * Getter for rotation.
     *
     * @return Rotate
     */
    public Rotate getRotate() {
        return rotate;
    }

    /**
     * This method sets new rotation.
     *
     * @param rotationX double
     * @param rotationY double
     * @param rotationZ double
     */
    public void setRotate(double rotationX, double rotationY, double rotationZ) {
        rotate = new Rotate(rotationX, rotationY, rotationZ);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Double.compare(cube.edge, edge) == 0
                && rotate.equals(((Cube) o).getRotate())
                && coordinate.equals(((Cube) o).coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotate, edge, coordinate);
    }

    public class Point {
        /**
         * Coordinate x.
         */
        private double x;
        /**
         * Coordinate y.
         */
        private double y;
        /**
         * Coordinate z.
         */
        private double z;
        /**
         * Constructor which takes three double coordinates: x,y,z.
         *
         * @param newX double coordinate x
         * @param newY double coordinate y
         * @param newZ double coordinate z
         */
        public Point(double newX, double newY, double newZ) {
            x = newX;
            y = newY;
            z = newZ;
        }

        /**
         * Getter for coordinate x.
         *
         * @return double coordinate x
         */
        public double getX() {
            return x;
        }

        /**
         * Setter for coordinate x.
         *
         * @param x new double value coordinate x
         */
        public void setX(double x) {
            this.x = x;
        }

        /**
         * Getter for coordinate y.
         *
         * @return double coordinate y
         */
        public double getY() {
            return y;
        }

        /**
         * Setter for coordinate y.
         *
         * @param y new double value coordinate y
         */
        public void setY(double y) {
            this.y = y;
        }

        /**
         * Getter for coordinate z.
         *
         * @return double coordinate z
         */
        public double getZ() {
            return z;
        }

        /**
         * Setter for coordinate z.
         *
         * @param z new double value coordinate z
         */
        public void setZ(double z) {
            this.z = z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0 && Double.compare(point.z, z) == 0;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
