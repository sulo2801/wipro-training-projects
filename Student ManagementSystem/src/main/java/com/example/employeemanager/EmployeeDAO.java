package com.example.employeemanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // ======================================
    // INSERT EMPLOYEE
    // ======================================
    public void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employee(name, department, salary) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());

            ps.executeUpdate();
        }
    }

    // ======================================
    // GET ALL EMPLOYEES
    // ======================================
    public List<Employee> getAllEmployees() throws SQLException {

        List<Employee> list = new ArrayList<>();

        String sql = "SELECT * FROM employee";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee e = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
                list.add(e);
            }
        }
        return list;
    }

    // ======================================
    // DELETE EMPLOYEE
    // ======================================
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // ======================================
    // GET EMPLOYEE BY ID (for editing)
    // ======================================
    public Employee getEmployeeById(int id) throws SQLException {

        String sql = "SELECT * FROM employee WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
            }
        }
        return null;
    }

    // ======================================
    // UPDATE EMPLOYEE
    // ======================================
    public void updateEmployee(Employee emp) throws SQLException {

        String sql = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.setInt(4, emp.getId());

            ps.executeUpdate();
        }
    }
}
