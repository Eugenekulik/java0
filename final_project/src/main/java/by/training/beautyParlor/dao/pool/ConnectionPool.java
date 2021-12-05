package by.training.beautyParlor.dao.pool;

import by.training.beautyParlor.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private String  url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;

    private BlockingDeque<PooledConnection> freeConnections = new LinkedBlockingDeque<>();
    private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    public ConnectionPool(){}

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    public static ConnectionPool getInstance(){
        return INSTANCE;
    }

    public synchronized PooledConnection getConnection() throws DaoException {
        PooledConnection connection = null;
        while(connection == null) {
            try {
                if(!freeConnections.isEmpty()){
                    connection = freeConnections.take();
                    if(!connection.isValid(checkConnectionTimeout)){
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                            LOGGER.warn("Connection closing error");
                        }
                        connection = null;
                    }
                }
                else if(usedConnections.size()<maxSize) {
                    connection = createConnection();
                }
                else {
                    LOGGER.error("The limit of number of databse connections is exceeded");
                    throw new DaoException();
                }
            } catch (InterruptedException | SQLException e) {
                LOGGER.error("It is impossible to connect to a database", e);
                throw new DaoException();
            }
        }
        usedConnections.add(connection);

        LOGGER.debug("The connection was received from pool");
        return connection;
    }
    public synchronized void freeConnection(PooledConnection connection){
        try {
            if(connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                LOGGER.debug("Connection was return into pool");
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.warn("it is impossible to return database connection into pool");
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {LOGGER.warn("Connection closing error");}
        }
    }

    public synchronized void destroy(){
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (PooledConnection c:usedConnections) {
            try {
                c.getConnection().close();
            } catch (SQLException e) {
                LOGGER.warn("Connection closing error");
            }
        }
        usedConnections.clear();
    }

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url,user,password));
    }

    public synchronized void init(String driverClass, String url, String user, String password,
                                  int startSize, int maxSize, int checkConnectionTimeout) throws DaoException {
        try {
            destroy();
            Class.forName(driverClass);
            this.url = url;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.checkConnectionTimeout = checkConnectionTimeout;
            for(int i = 0; i < startSize; i++) {
                freeConnections.put(createConnection());
            }
        } catch (InterruptedException | SQLException | ClassNotFoundException e) {
            LOGGER.error("It is impossible to initialize connection pool", e);
            throw new DaoException();
        }
    }
}
