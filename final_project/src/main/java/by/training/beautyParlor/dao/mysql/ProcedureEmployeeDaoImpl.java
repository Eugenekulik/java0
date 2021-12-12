package by.training.beautyParlor.dao.mysql;

import by.training.beautyParlor.domain.Procedure;
import by.training.beautyParlor.domain.User;
import by.training.beautyParlor.dao.DaoException;
import by.training.beautyParlor.dao.ProcedureEmployeeDao;
import by.training.beautyParlor.domain.ProcedureEmployee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implement ProcedureEmployeeDao for database MySQL.
 */

public class ProcedureEmployeeDaoImpl implements ProcedureEmployeeDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String SQL_FIND_BY_PROCEDURE_EMPLOYEE = "SELECT procedure_employee.id, procedure_employee.employee_id, " +
            "procedure_employee.procedure_id, procedure_employee.price, procedure_employee.rating " +
            "FROM procedure_employee WHERE procedure_employee.procedure_id = ? " +
            "AND procedure_employee.employee_id = ?;";
    private static final String SQL_FIND_INTERVAL = "SELECT procedure_employee.id, procedure_employee.employee_id, " +
            "procedure_employee.procedure_id, procedure_employee.price, procedure_employee.rating " +
            "FROM procedure_employee WHERE procedure_employee.id>0 LIMIT ?, ?;";
    private Connection connection;
    private static final String SQL_CREATE = "INSERT INTO procedure_employee(" +
            "procedure_employee.employee_id, procedure_employee.procedure_id, " +
            "procedure_employee.price, procedure_employee.rating) VALUES(?,?,?,?);";
    private static final String SQL_FIND_ALL = "SELECT procedure_employee.id, procedure_employee.employee_id, " +
            "procedure_employee.procedure_id, procedure_employee.price, procedure_employee.rating " +
            "FROM procedure_employee;";
    private static final String SQL_FIND_BY_ID = "SELECT procedure_employee.id, procedure_employee.employee_id, " +
            "procedure_employee.procedure_id, procedure_employee.price, procedure_employee.rating " +
            "FROM procedure_employee WHERE procedure_employee.id = ?";
    private static final String SQL_DELETE = "DELETE FROM procedure_employee " +
            "WHERE procedure_employee.id = ?;";
    private static final String SQL_UPDATE = "UPDATE procedure_employee SET " +
            "procedure_employee.employee_id= ?, procedure_employee.procedure_id = ?, " +
            "procedure_employee.price = ?, procedure_employee.rating = ? " +
            "WHERE procedure_employee.id = ?;";
    private static final String SQL_FIND_BY_PROCEDURE = "SELECT procedure_employee.id, procedure_employee.employee_id, " +
            "procedure_employee.procedure_id, procedure_employee.price, procedure_employee.rating " +
            "FROM procedure_employee WHERE procedure_employee.procedure_id = ?;";

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
                procedureEmployee.setPrice(resultSet.getDouble("procedure_employee.price"));
                procedureEmployee.setRating(resultSet.getDouble("procedure_employee.rating"));
                procedureEmployees.add(procedureEmployee);
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
        return procedureEmployees;
    }
    @Override
    public int count() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count  = 0;
        try {
            statement = connection.prepareStatement("SELECT count(procedure_employee.id) as count " +
                    "FROM procedure_employee");
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
        return count;
    }
    @Override
    public List<ProcedureEmployee> findInterval(int begin, int count) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ProcedureEmployee> procedureEmployees = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_INTERVAL);
            statement.setInt(1,begin);
            statement.setInt(2, count);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                ProcedureEmployee procedureEmployee = new ProcedureEmployee();
                procedureEmployee.setId(resultSet.getInt("procedure_employee.id"));
                procedureEmployee.setEmployeeId(resultSet.getInt("procedure_employee.employee_id"));
                procedureEmployee.setProcedureId(resultSet.getInt("procedure_employee.procedure_id"));
                procedureEmployee.setPrice(resultSet.getDouble("procedure_employee.price"));
                procedureEmployee.setRating(resultSet.getDouble("procedure_employee.rating"));
                procedureEmployees.add(procedureEmployee);
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
        return procedureEmployees;
    }

    @Override
    public ProcedureEmployee findById(int id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ProcedureEmployee procedureEmployee = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1,id);
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                procedureEmployee = new ProcedureEmployee();
                procedureEmployee.setId(resultSet.getInt("procedure_employee.id"));
                procedureEmployee.setEmployeeId(resultSet.getInt("procedure_employee.employee_id"));
                procedureEmployee.setProcedureId(resultSet.getInt("procedure_employee.procedure_id"));
                procedureEmployee.setPrice(resultSet.getDouble("procedure_employee.price"));
                procedureEmployee.setRating(resultSet.getDouble("procedure_employee.rating"));
            }
            return procedureEmployee;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
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
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
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
            statement.setDouble(3, procedureEmployee.getPrice());
            statement.setDouble(4, procedureEmployee.getRating());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
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
            statement.setDouble(3, procedureEmployee.getPrice());
            statement.setDouble(4, procedureEmployee.getRating());
            statement.setInt(5,procedureEmployee.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ProcedureEmployee> findByProcedure(Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ProcedureEmployee> procedureEmployees = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_PROCEDURE);
            statement.setInt(1, procedure.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                ProcedureEmployee procedureEmployee = new ProcedureEmployee();
                procedureEmployee.setId(resultSet.getInt("procedure_employee.id"));
                procedureEmployee.setEmployeeId(resultSet.getInt("procedure_employee.employee_id"));
                procedureEmployee.setProcedureId(resultSet.getInt("procedure_employee.procedure_id"));
                procedureEmployee.setPrice(resultSet.getDouble("procedure_employee.price"));
                procedureEmployee.setRating(resultSet.getDouble("procedure_employee.rating"));
                procedureEmployees.add(procedureEmployee);
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
        return procedureEmployees;
    }

    @Override
    public ProcedureEmployee findByProcedureEmployee(Procedure procedure, User employee) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ProcedureEmployee procedureEmployee = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_PROCEDURE_EMPLOYEE);
            statement.setInt(1, procedure.getId());
            statement.setInt(2, employee.getId());
            statement.execute();
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                procedureEmployee = new ProcedureEmployee();
                procedureEmployee.setId(resultSet.getInt("procedure_employee.id"));
                procedureEmployee.setEmployeeId(resultSet.getInt("procedure_employee.employee_id"));
                procedureEmployee.setProcedureId(resultSet.getInt("procedure_employee.procedure_id"));
                procedureEmployee.setPrice(resultSet.getDouble("procedure_employee.price"));
                procedureEmployee.setRating(resultSet.getDouble("procedure_employee.rating"));
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
                throw new DaoException();
            }
        }
        return procedureEmployee;
    }
}
