package by.training.task5.bean;


public class Matrix {
    /**
     * Variable keep the vertical size of matrix.
     */
    private int vertical;
    /**
     * Variable keep the horizontal size of matrix.
     */
    private int horizontal;
    /**
     * Double array which keep the values of matrix.
     */
    private int[][] value;

    /**
     * Constructor which takes the sizes of matrix.
     * @param n vertical size
     * @param m horizontal size
     */
    public Matrix(int n, int m) {
        vertical = n;
        horizontal = m;
        value = new int[vertical][horizontal];
    }
    /**
     * Getter for vertical size.
     * @return vertical size
     */
    public int getVertical() {
        return vertical;
    }
    /**
     * Getter for horizontal size.
     * @return horizontal size
     */
    public int getHorizontal() {
        return horizontal;
    }

    /**
     * This method return value by index [i,j].
     * @param i vertical
     * @param j horizontal
     * @return value by this index
     */
    public int get(int i, int j) {
        return value[i][j];
    }
    /**
     * This method change value by index [i,j].
     * @param i vertical
     * @param j horizontal
     * @param val new value
     */
    public void set(int i, int j, int val) {
        value[i][j] = val;
    }

    /**
     * Override Object's method which generate hashCode.
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    /**
     * Override Object's method which equels two objects.
     * @param obj object with which to compare
     * @return true if objects are equals
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    /**
     * Generate String's perfomance of class's object.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] i:value) {
            for (int j:i) {
                stringBuilder.append(j + " ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
