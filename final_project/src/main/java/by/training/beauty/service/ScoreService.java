package by.training.beauty.service;

import by.training.beauty.dao.*;
import by.training.beauty.dao.mysql.DaoEnum;
import by.training.beauty.dao.mysql.TransactionFactoryImpl;
import by.training.beauty.dao.spec.*;
import by.training.beauty.domain.Appointment;
import by.training.beauty.domain.Procedure;
import by.training.beauty.domain.ProcedureEmployee;
import by.training.beauty.domain.Score;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ScoreService {
    private static final Logger LOGGER = LogManager.getLogger(ScoreService.class);

    public boolean addScore(Score score) {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScoreDao scoreDao = transaction.createDao("scoreDao");
            scoreDao.create(score);
            transaction.commit();
            return true;
        } catch (DaoException e) {
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

    public List<Score> getScoreByProcedure(Procedure procedure) {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        List<Score> scores = new ArrayList<>();
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            ScoreDao scoreDao = transaction.createDao("scoreDao");
            ProcedureEmployeeDao procedureEmployeeDao =
                    transaction.createDao("procedureEmployeeDao");
            AppointmentDao appointmentDao = transaction.createDao("appointmentDao");
            List<ProcedureEmployee> procedureEmployeeList = procedureEmployeeDao.findByProcedure(procedure);
            List<Appointment> appointments = new ArrayList<>();
            for (ProcedureEmployee temp : procedureEmployeeList) {
                appointments.addAll(appointmentDao.getEmployeeAppointment(temp));
            }
            for (Appointment temp : appointments) {
                scores.addAll(scoreDao.findByAppointment(temp));
            }
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

    public boolean deleteScore(int id) {
        TransactionFactory transactionFactory = null;
        Transaction transaction = null;
        try{
           transactionFactory = new TransactionFactoryImpl();
           transaction = transactionFactory.createTransaction();
           ScoreDao scoreDao = transaction.createDao(DaoEnum.SCORE.getDao());
           scoreDao.delete(id);
           transaction.commit();
           return true;
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
