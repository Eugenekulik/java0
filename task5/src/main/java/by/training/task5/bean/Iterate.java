package by.training.task5.bean;

public class Iterate {
    public Integer getActual() {
        return actual;
    }

    private Integer actual;
    public Iterate(){
        actual = 0;
    }
    public void plus(){
        actual++;
    }
}
