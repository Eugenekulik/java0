package by.training.task5.service;

import by.training.task5.bean.Matrix;
import by.training.task5.dao.DaoException;
import by.training.task5.dao.DaoFactory;

public class MatrixCreator {
    String file;
    public MatrixCreator(String file){
        this.file = file;
    }
    public Matrix create() throws ServiceException {
        try{
            Parser parser = new Parser(DaoFactory.getInstance().getReaderDao(file).read());
            int []data = parser.parse();
            if(data.length<3){
                throw new ServiceException("too little data");
            }
            if(data.length!=2+(data[0]*data[1])){
                throw new ServiceException("too much or too little matrix's values");
            }
            Matrix matrix = new Matrix(data[0],data[1]);
            for(int i=0;i< matrix.getVertical();i++){
                for(int j=0;j< matrix.getHorizontal();j++){
                    matrix.set(i,j,data[matrix.getVertical()*i+j+2]);
                }
            }
            return matrix;
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
