package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.RoleDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Role;
import by.training.beauty.service.ServiceException;
import by.training.beauty.service.spec.RoleService;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class RoleServiceImplTest {

    private TransactionFactory transactionFactory;

    @BeforeClass
    public void init() {
        transactionFactory = mock(TransactionFactory.class);
        Transaction transaction = mock(Transaction.class);
        RoleDao roleDao = mock(RoleDao.class);
        try{
            when(roleDao.findall()).thenReturn(List.of(new Role(1,"admin")));
            when(transaction.createDao(DaoEnum.ROLE.getDao())).thenReturn(roleDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (DaoException e){e.printStackTrace();}
    }

    @Test
    public void testGetAllRoles() {
        RoleService roleService = new RoleServiceImpl(transactionFactory);
        try {
            Assertions.assertThat(roleService.getAllRoles())
                    .usingRecursiveFieldByFieldElementComparator()
                    .contains(new Role(1,"admin"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}