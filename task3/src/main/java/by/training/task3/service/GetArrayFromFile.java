package by.training.task3.service;

import by.training.task3.bean.MyArray;
import by.training.task3.dao.DaoException;
import by.training.task3.dao.DaoFactory;
import by.training.task3.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.Scanner;

public class GetArrayFromFile<T extends Comparable>{
    private String filePath;
    private static final Logger logger = LogManager.getLogger();
    public GetArrayFromFile(String filePath){
        this.filePath=filePath;
    }
    public MyArray create(Class<T> type) throws ServiceException {
        logger.info("Get array from file run");
        try {
            Scanner scanner = DaoFactory.getInstance().getScanner(filePath);
            int size= Integer.parseInt(scanner.next());
            MyArray<T> array = new MyArray<T>(type,size);

            for(int i=0;i<size;i++) {
                switch (type.getName()) {

                    case "java.lang.Integer":
                        Integer tempInteger = Integer.parseInt(scanner.next());
                        array.set(i, (T) tempInteger);
                        break;

                    case "java.lang.Double":
                        Double tempDouble = Double.parseDouble(scanner.next());
                        array.set(i, (T) tempDouble);
                        break;

                    case "java.lang.Float":
                        Float tempFloat = Float.parseFloat(scanner.next());
                        array.set(i, (T) tempFloat);
                        break;

                    case "java.lang.Byte":
                        Byte tempByte = Byte.parseByte(scanner.next());
                        array.set(i, (T) tempByte);
                        break;

                    case "java.lang.Short":
                        Short tempShort = Short.parseShort(scanner.next());
                        array.set(i, (T) tempShort);
                        break;

                    case "java.lang.String":
                        array.set(i, (T) scanner.next());
                        break;

                    case "java.lang.Long":
                        Long tempLong = Long.parseLong(scanner.next());
                        array.set(i, (T) tempLong);
                        break;

                    default:
                        return null;
                }
            }
            logger.info("Get array from file completed");
            return array;
        }
        catch (DaoException|NumberFormatException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

}
