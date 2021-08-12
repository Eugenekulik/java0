package by.training.task2.service;

/**
 * This class calculate product sequence.
 */
public class ProductSequence {
    public ProductSequence(){};

    /**
     * This method do calculations.
     * @return product
     */
    public double execute(){
        double[]a=new double[10];
        a[0]=1;
        double result=a[0];
        for(int i=1;i<10;i++){
            a[i]=6+a[i-1];
            result*=a[i];
        }
        return result;
    }
}
