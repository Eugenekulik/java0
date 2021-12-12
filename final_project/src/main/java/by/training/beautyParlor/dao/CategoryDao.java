package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Appointment;
import by.training.beautyParlor.domain.Category;

/**
 * This interface extends Dao and
 * add functional work with Category.
 *
 * @see Dao
 * @see Category
 */

public interface CategoryDao extends Dao<Category> {
    /**
     * This method allows getting Category by name.
     * @param name
     * @return Category, which we want to find and null if it doesn't exist.
     * @throws DaoException
     */
    Category findByName(String name) throws DaoException;
}
