package by.training.task5.bean;

/**
 * Class Iterate give oppotunite to follow the actual iterate.
 */
public class Iterate {
    /**
     * Varialble keep the number of actual itarete.
     */
    private Integer actual;
    /**
     * getter for actual.
     * @return Integer
     */
    public Integer getActual() {
        return actual;
    }
    /**
     * Constructor.
     */
    public Iterate() {
        actual = 0;
    }
    /**
     * Increment actual.
     */
    public void plus() {
        actual++;
    }
}
