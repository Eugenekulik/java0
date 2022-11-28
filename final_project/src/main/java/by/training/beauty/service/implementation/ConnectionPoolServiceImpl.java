package by.training.beauty.service.implementation;


import by.training.beauty.dao.pool.ConnectionPool;
import by.training.beauty.dao.DaoException;
import by.training.beauty.service.spec.ConnectionPoolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * This class allows work with connection pool.
 */
public class ConnectionPoolServiceImpl implements ConnectionPoolService {
    private static final Logger LOGGER
            = LogManager.getLogger(ConnectionPoolService.class);

    /**
     * This method initialize connection pool with properties
     * which are stored in the file "connection.properties".
     */
    @Override public void init(){
        Properties properties = new Properties();
        FileReader fileReader = null;
        try {
            URL resource = getClass().getClassLoader()
                    .getResource("connection.properties");
            fileReader = new FileReader(new File(resource.toURI()));
            properties.load(fileReader);
            ConnectionPool.getInstance()
                    .init(properties.getProperty("db.driver"),
                            properties.getProperty("db.url"),
                            properties.getProperty("user"),
                            properties.getProperty("password"), 1,
                    Integer.parseInt(properties.getProperty("poolsize")),
                    30);
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("It is impossible to load properties", e);
        } catch (DaoException e) {
            LOGGER.error("It is impossible to init connection pool " +
                    "to database", e);
        }
        finally {
            try {
                if(fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                LOGGER.error("an error occurred while closing FileReader");
            }
        }
    }

    /**
     * This method allows destroy connection pool.
     */
    @Override public void destroy(){
        ConnectionPool.getInstance().destroy();
    }
}
