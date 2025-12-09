package com.example.employeemanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Employees")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EmployeeDAO dao;

    public void init() {
        dao = new EmployeeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEmployee(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployees(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // ---------------------------
    // LIST EMPLOYEES
    // ---------------------------
    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Employee> list = dao.getAllEmployees();
        request.setAttribute("employees", list);
        request.getRequestDispatcher("employees/list-employees.jsp").forward(request, response);
    }

    // ---------------------------
    // SHOW NEW FORM
    // ---------------------------
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("employees/add-edit-employee.jsp").forward(request, response);
    }

    // ---------------------------
    // INSERT EMPLOYEE
    // ---------------------------
    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));

        Employee newEmp = new Employee(0, name, department, salary);
        dao.addEmployee(newEmp);

        response.sendRedirect("EmployeeServlet?action=list");
    }

    // ---------------------------
    // DELETE EMPLOYEE
    // ---------------------------
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteEmployee(id);

        response.sendRedirect("EmployeeServlet?action=list");
    }

    // ---------------------------
    // SHOW EDIT FORM
    // ---------------------------
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = dao.getEmployeeById(id);

        request.setAttribute("employee", existingEmployee);
        request.getRequestDispatcher("employees/add-edit-employee.jsp").forward(request, response);
    }

    // ---------------------------
    // UPDATE EMPLOYEE
    // ---------------------------
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));

        Employee updatedEmp = new Employee(id, name, department, salary);
        dao.updateEmployee(updatedEmp);

        response.sendRedirect("EmployeeServlet?action=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
