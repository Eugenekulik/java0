package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.Category;
import by.training.beauty_parlor.exception.DaoException;

public interface CategoryDao extends Dao<Category> {
    Category findByName(String name) throws DaoException;
}
