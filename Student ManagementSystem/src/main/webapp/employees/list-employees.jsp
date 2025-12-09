<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<html>
<head>
    <title>Employee List</title>
</head>
<body>

<h2>Employee List</h2>

<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Department</th>
        <th>Salary</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="emp" items="${employees}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.department}</td>
            <td>${emp.salary}</td>

            <td>
                <a href="EmployeeServlet?action=edit&id=${emp.id}">Edit</a> |
                <a href="EmployeeServlet?action=delete&id=${emp.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="EmployeeServlet?action=new">Add Employee</a>

</body>
</html>
