package by.training.beauty.service.spec;

import by.training.beauty.domain.Role;
import by.training.beauty.service.ServiceException;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles() throws ServiceException;
}
