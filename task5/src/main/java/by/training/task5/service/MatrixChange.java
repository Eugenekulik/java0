package by.training.task5.service;

/**
 * This interface which give functional to any change with matrix.
 * It have single method change()
 */
public interface MatrixChange {
    /**
     * This method do any changes with matrix.
     * @throws ServiceException
     */
     void change()throws ServiceException;
}
