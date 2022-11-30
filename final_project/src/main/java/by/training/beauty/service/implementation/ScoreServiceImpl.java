package by.training.beauty.service.implementation;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Score;
import by.training.beauty.service.spec.ScoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ScoreServiceImpl implements ScoreService {
    private static final Logger LOGGER = LogManager.getLogger(ScoreService.class);
    private TransactionFactory transactionFactory;

    public ScoreServiceImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    @Override public Score addScore(Score score) {
        Transaction transaction = null;
        try {
            transaction = transactionFactory.createTransaction();
            ScoreDao scoreDao = transaction.createDao("scoreDao");
            score = scoreDao.create(score);
            transaction.commit();
            return score;
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error("It is impossible to rollback transaction");
            }
        }
        return null;
    }

    @Override public List<Score> getScoresByProcedure(Procedure procedure) {
        Transaction transaction = null;
        List<Score> scores = new ArrayList<>();
        try {
            transaction = transactionFactory.createTransaction();
            ScoreDao scoreDao = transaction.createDao("scoreDao");
            scores.addAll(scoreDao.findByProcedure(procedure.getId()));
            transaction.commit();
        } catch (DaoException e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error("It is impossible to rollback transaction");
            }
        }
        return scores;
    }

    @Override public boolean deleteScore(int id) {
        Transaction transaction = null;
        try{
           transaction = transactionFactory.createTransaction();
           ScoreDao scoreDao = transaction.createDao(DaoEnum.SCORE.getDao());
           boolean result = scoreDao.delete(id);
           transaction.commit();
           return result;
        } catch (DaoException e){
            LOGGER.error(()->"Error occured while trying to delete score: " + e.getMessage());
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (DaoException e1) {
                LOGGER.error("It is impossible to rollback transaction");
            }
        }
        return false;
    }
}
