package by.training.beauty.service.implementation;

import by.training.beauty.dao.DaoException;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.spec.ScoreDao;
import by.training.beauty.dao.spec.Transaction;
import by.training.beauty.dao.spec.TransactionFactory;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.Score;
import by.training.beauty.service.spec.ScoreService;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScoreServiceImplTest {
    TransactionFactory transactionFactory;


    @BeforeClass
    public void init() {
        transactionFactory = mock(TransactionFactory.class);
        Transaction transaction = mock(Transaction.class);
        ScoreDao scoreDao = mock(ScoreDao.class);
        try {
// Configure mock scoreDao
            when(scoreDao.create(any())).thenReturn(new Score());
            when(scoreDao.delete(1)).thenReturn(true);
            when(scoreDao.delete(2)).thenReturn(false);
            when(scoreDao.findByProcedure(1))
                    .thenReturn(List.of(new Score.Builder()
                            .setId(1)
                            .build()));


// Configure mock transaction
            when(transaction.createDao(DaoEnum.SCORE.getDao())).thenReturn(scoreDao);
            when(transactionFactory.createTransaction()).thenReturn(transaction);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAddScore() {
        ScoreService scoreService = new ScoreServiceImpl(transactionFactory);
        Assertions.assertThat(scoreService.addScore(new Score())).isNotNull();
    }


    @Test
    public void testDelete_Return_True(){
        ScoreService scoreService = new ScoreServiceImpl(transactionFactory);
        Assertions.assertThat(scoreService.deleteScore(1)).isTrue();
    }

    @Test
    public void testDelete_Return_False(){
        ScoreService scoreService = new ScoreServiceImpl(transactionFactory);
        Assertions.assertThat(scoreService.deleteScore(2)).isFalse();
    }


    @Test
    public void testGetScoresByProcedure(){
        ScoreService scoreService = new ScoreServiceImpl(transactionFactory);
        Assertions.assertThat(scoreService.getScoresByProcedure(new Procedure.Builder()
                .setId(1).build()))
                .usingElementComparator(Comparator.comparing(Score::getId))
                .contains(new Score.Builder().setId(1).build())
                .size().isEqualTo(1);
    }

}