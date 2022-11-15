package by.training.beauty.service;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.domain.Role;
import by.training.beauty.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RoleService{
    private static final Logger LOGGER = LogManager.getLogger(RoleService.class);


    public List<Role> getAllRoles() throws ServiceException {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            RoleDao roleDao = transaction.createDao(DaoEnum.ROLE.getDao());
            List<Role> roles = roleDao.findall();
            transaction.commit();
            return roles;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error("it is impossible to rollback transaction",e1);
            }
            throw new ServiceException(e);
        }
    }
}
