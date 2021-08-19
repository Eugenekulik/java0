package by.training.task3.service.matrixOperation;

import by.training.task3.bean.IntegerMatrix;
import by.training.task3.service.ServiceException;

public class MatrixProduct {
    public MatrixProduct(){};
    public IntegerMatrix result(IntegerMatrix a, IntegerMatrix b) throws ServiceException {
        if(a.getHorizontalSize()==b.getVerticalSize()){
            Integer temp = 0;
            IntegerMatrix c = new IntegerMatrix(a.getVerticalSize(),b.getHorizontalSize());
            for(int i=0;i<c.getVerticalSize();i++){
                for(int j=0;j<c.getHorizontalSize();j++){
                    for(int k=0;k<a.getHorizontalSize();k++){
                        temp += b.getElement(k,i)*a.getElement(j,k);
                    }
                    c.setElement(i,j,temp);
                    temp = 0;
                }
            }
            return c;
        }
        else if(a.getVerticalSize()==b.getHorizontalSize()){
            Integer temp = 0;
            IntegerMatrix c = new IntegerMatrix(a.getHorizontalSize(),b.getVerticalSize());
            for(int i=0;i<c.getVerticalSize();i++){
                for(int j=0;j<c.getHorizontalSize();j++){
                    for(int k=0;k<a.getHorizontalSize();k++){
                        temp += b.getElement(i,k)*a.getElement(k,j);
                    }
                    c.setElement(i,j,temp);
                    temp = 0;
                }
            }
            return c;
        }
        else{
            throw new ServiceException("these matrix can't be multiply");
        }

    }
}
