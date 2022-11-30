package by.training.beauty.service.spec;

import by.training.beauty.domain.Category;
import by.training.beauty.domain.Procedure;
import by.training.beauty.service.ServiceException;

import java.util.List;

public interface ProcedureService {
    List<Procedure> getProcedures() throws ServiceException;

    Procedure getProcedureById(int id) throws ServiceException;

    List<Category> getCategories() throws ServiceException;

    boolean addProcedure(Procedure procedure) throws ServiceException;

    boolean deleteProcedure(int id) throws ServiceException;

    boolean updateProcedure(Procedure procedure) throws ServiceException;
}
