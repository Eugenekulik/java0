package by.training.beauty.dao.mysql;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.spec.CategoryDao;
import by.training.beauty.domain.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement CategoryDao for database MySQL.
 */

public class CategoryDaoImpl implements CategoryDao {
    private static final Logger LOGGER = LogManager.getLogger(CategoryDaoImpl.class);
    private Connection connection;
    private static final String SQL_FIND_INTERVAL = "SELECT category.id, category.name, " +
            "category.description FROM category WHERE category.id>0 LIMIT ?, ?;";
    private static final String SQL_FIND_BY_NAME = "SELECT category.id, category.name, " +
            "category.description FROM category WHERE category.name = ?;";
    private static final String SQL_FIND_BY_ID = "SELECT category.id, category.name, " +
            "category.description FROM category WHERE category.id = ?;";
    private static final String SQL_FIND_ALL = "SELECT category.id, category.name, " +
            "category.description FROM category;";
    private static final String SQL_DELETE = "DELETE FROM category WHERE category.id = ?;";
    private static final String SQL_CREATE = "INSERT INTO category(category.id, " +
            "category.name, category.description) VALUES (?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE category SET category.name, " +
            "category.description WHERE category.id = ?";


    @Override
    public Category findByName(String name) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Category category = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_NAME);
            statement.setString(1, name);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("category.id"));
                category.setName(resultSet.getString("category.name"));
                category.setDescription(resultSet.getString("category.description"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return category;
    }

    @Override
    public List<Category> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("category.id"));
                category.setName(resultSet.getString("category.name"));
                category.setDescription(resultSet.getString("category.description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return categories;
    }

    @Override
    public List<Category> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1, begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("category.id"));
                category.setName(resultSet.getString("category.name"));
                category.setDescription(resultSet.getString("category.description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return categories;
    }

    @Override
    public Category findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Category category = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("category.id"));
                category.setName(resultSet.getString("category.name"));
                category.setDescription(resultSet.getString("category.description"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return category;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public Category create(Category category) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, category.getId());
            statement.setString(2,category.getName());
            statement.setString(3,category.getDescription());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while(resultSet.next()){
                category.setId(resultSet.getInt("GENERATED_KEY"));
                return category;
            }
            return null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public boolean update(Category category) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1,category.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(category.id) as count " +
                    "FROM category");
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return count;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
