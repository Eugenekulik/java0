package by.training.beautyParlor.dao;

import by.training.beautyParlor.domain.Graphic;

import java.time.LocalDateTime;
import java.util.List;

public interface GraphicDao extends Dao<Graphic>{
    List<Graphic> findByEmployee(int employeeId) throws DaoException;
    Graphic findByDate(LocalDateTime time) throws DaoException;
}
