package by.training.beauty.service.spec;

import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Score;

import java.util.List;

public interface ScoreService {
    boolean addScore(Score score);

    List<Score> getScoreByProcedure(Procedure procedure);

    boolean deleteScore(int id);
}
