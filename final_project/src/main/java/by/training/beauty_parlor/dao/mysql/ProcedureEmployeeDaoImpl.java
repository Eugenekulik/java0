package by.training.beauty_parlor.dao.mysql;

import by.training.beauty_parlor.exception.DaoException;
import by.training.beauty_parlor.dao.ProcedureEmployeeDao;
import by.training.beauty_parlor.domain.ProcedureEmployee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureEmployeeDaoImpl implements ProcedureEmployeeDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private Connection connection;
    private static final String SQL_CREATE = "INSERT INTO procedure_employee(" +
            "procedure_employee.employee_id, procedure_employee.procedure_id, " +
            "procedure_employee.price, procedure_employee.rating) VALUES(?,?,?,?);";
    private static final String SQL_FIND_ALL = "SELECT procedure_employee.id, procedure_employee.employee_id, " +
            "procedure_employee.procedure_id, procedure_emplouee.price, procedure_employee.rating " +
            "FROM procedure_employee;";
    private static final String SQL_FIND_BY_ID = "SELECT procedure_employee.id, procedure_employee.employee_id, " +
            "procedure_employee.procedure_id, procedure_emplouee.price, procedure_employee.rating " +
            "FROM procedure_employee WHERE procedure_employee.id = ?";
    private static final String SQL_DELETE = "DELETE FROM procedure_employee " +
            "WHERE procedure_employee.id = ?;";
    private static final String SQL_UPDATE = "UPDATE procedure_employee SET " +
            "procedure_employee.employee_id= ?, procedure_employee.procedure_id = ?, " +
            "procedure_employee.price = ?, procedure_employee.rating = ? " +
            "WHERE procedure_employee.id = ?;";

    @Override
    public List<ProcedureEmployee> findall() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ProcedureEmployee> procedureEmployees = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                ProcedureEmployee procedureEmployee = new ProcedureEmployee();
                procedureEmployee.setId(resultSet.getInt("procedure_employee.id"));
                procedureEmployee.setEmployeeId(resultSet.getInt("procedure_employee.employee_id"));
                procedureEmployee.setProcedureId(resultSet.getInt("procedure_employee.procedure_id"));
                procedureEmployee.setPrice(resultSet.getInt("procedure_employee.price"));
                procedureEmployee.setRating(resultSet.getInt("procedure_employee.rating"));
                procedureEmployees.add(procedureEmployee);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
        return procedureEmployees;
    }

    @Override
    public ProcedureEmployee findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ProcedureEmployee procedureEmployee = new ProcedureEmployee();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                procedureEmployee.setId(resultSet.getInt("procedure_employee.id"));
                procedureEmployee.setEmployeeId(resultSet.getInt("procedure_employee.employee_id"));
                procedureEmployee.setProcedureId(resultSet.getInt("procedure_employee.procedure_id"));
                procedureEmployee.setPrice(resultSet.getInt("procedure_employee.price"));
                procedureEmployee.setRating(resultSet.getInt("procedure_employee.rating"));
            }
            return procedureEmployee;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean create(ProcedureEmployee procedureEmployee) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, procedureEmployee.getEmployeeId());
            statement.setInt(2, procedureEmployee.getProcedureId());
            statement.setInt(3, procedureEmployee.getPrice());
            statement.setInt(4, procedureEmployee.getRating());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public boolean update(ProcedureEmployee procedureEmployee) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, procedureEmployee.getEmployeeId());
            statement.setInt(2, procedureEmployee.getProcedureId());
            statement.setInt(3, procedureEmployee.getPrice());
            statement.setInt(4, procedureEmployee.getRating());
            statement.setInt(5,procedureEmployee.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
