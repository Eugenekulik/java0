package by.training.beauty_parlor.dao;

import by.training.beauty_parlor.domain.Graphic;

import java.time.LocalDateTime;
import java.util.List;

public interface GraphicDao extends Dao<Graphic>{
    List<Graphic> findByEmployee(int employeeId) throws DaoException;
    Graphic findByDate(LocalDateTime time) throws DaoException;
}
