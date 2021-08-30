package by.training.task4.Dao;

import by.training.task4.bean.Country;

import java.io.*;

public class CountryDao {
    File file;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public CountryDao() {
    }

    public void init(String filePath) throws DaoException {
        file = new File(filePath);
    }

    public Country read() throws DaoException {
        try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                return (Country) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DaoException(e);
        }
    }

    public void write(Country country) throws DaoException {
        try {
            if(!file.exists()){
                file.delete();
                file.createNewFile();
            }
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(country);
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
