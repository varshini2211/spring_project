package com.kgisl.springproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (empId, firstname, lastname, department, salary, dateOfJoining) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE empId = ?";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE empId = ?";
    private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET firstname = ?, lastname = ?, department = ?, salary = ?, dateOfJoining = ? WHERE empId = ?;";

    public EmployeeDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertEmployee(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getFirstname());
            preparedStatement.setString(3, employee.getLastname());
            preparedStatement.setString(4, employee.getDepartment());
            preparedStatement.setString(5, employee.getSalary());
            preparedStatement.setString(6, employee.getDateOfJoining());
            preparedStatement.executeUpdate();
        }
    }

    public Employee selectEmployee(long id) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int empId = resultSet.getInt("empId");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String department = resultSet.getString("department");
                String salary = resultSet.getString("salary");
                String dateOfJoining = resultSet.getString("dateOfJoining");
                employee = new Employee(empId, firstname, lastname, department, salary, dateOfJoining);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> SELECT_ALL_EMPLOYEES() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_EMPLOYEES)) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("empId"));
                emp.setFirstname(rs.getString("firstname"));
                emp.setLastname(rs.getString("lastname"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getString("salary"));
                emp.setDateOfJoining(rs.getString("dateOfJoining"));
                employees.add(emp);
            }
        }
        return employees;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
    

    public boolean updateEmployee(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            statement.setString(1, employee.getFirstname());
            statement.setString(2, employee.getLastname());
            statement.setString(3, employee.getDepartment());
            statement.setString(4, employee.getSalary());
            statement.setString(5, employee.getDateOfJoining());
            statement.setInt(6, employee.getEmpId());
            return statement.executeUpdate() > 0;
        }
    }
}
