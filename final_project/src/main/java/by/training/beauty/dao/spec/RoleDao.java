package by.training.beauty.dao.spec;

import by.training.beauty.dao.DaoException;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;

import java.util.List;

public interface RoleDao extends Dao<Role>{

    List<Role> findByUser(User user) throws DaoException;
}
