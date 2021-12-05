package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Category;

public interface CategoryDao extends Dao<Category> {
    Category findByName(String name) throws DaoException;
}
