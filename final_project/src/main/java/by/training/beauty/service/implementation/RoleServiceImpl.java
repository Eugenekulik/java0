package by.training.beauty.service.implementation;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.RoleDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Role;
import by.training.beauty.service.spec.RoleService;
import by.training.beauty.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LogManager.getLogger(RoleService.class);
    private TransactionFactory transactionFactory;

    public RoleServiceImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }


    @Override public List<Role> getAllRoles() throws ServiceException {
        Transaction transaction = null;
        try {
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
