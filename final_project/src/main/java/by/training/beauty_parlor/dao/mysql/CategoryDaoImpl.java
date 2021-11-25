package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.dao.DaoException;
import by.training.beauty_parlor.dao.CategoryDao;
import by.training.beauty_parlor.domain.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean create(Category category) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, category.getId());
            statement.setString(2,category.getName());
            statement.setString(3,category.getDescription());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }
}