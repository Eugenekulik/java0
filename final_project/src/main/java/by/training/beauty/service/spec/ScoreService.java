package by.training.beauty.service.spec;

import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Score;

import java.util.List;

public interface ScoreService {
    Score addScore(Score score);

    List<Score> getScoresByProcedure(Procedure procedure);

    boolean deleteScore(int id);
}
