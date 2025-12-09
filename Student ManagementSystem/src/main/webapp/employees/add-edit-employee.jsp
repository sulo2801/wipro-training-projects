<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add / Edit Employee</title>
</head>
<body>

<h2>
    <c:if test="${employee != null}">
        Edit Employee
    </c:if>
    <c:if test="${employee == null}">
        Add New Employee
    </c:if>
</h2>

<form action="${pageContext.request.contextPath}/EmployeeServlet" method="post">

    <input type="hidden" name="action" 
           value="<c:if test='${employee != null}'>update</c:if><c:if test='${employee == null}'>insert</c:if>">

    <c:if test="${employee != null}">
        <input type="hidden" name="id" value="${employee.id}">
    </c:if>

    Name: <input type="text" name="name" value="${employee.name}" required><br><br>

    Department: <input type="text" name="department" value="${employee.department}" required><br><br>

    Salary: <input type="number" step="0.01" name="salary" value="${employee.salary}" required><br><br>

    <button type="submit">
        <c:if test="${employee != null}">Update</c:if>
        <c:if test="${employee == null}">Add</c:if>
    </button>

</form>

<br>
<a href="${pageContext.request.contextPath}/EmployeeServlet?action=list">Back to List</a>

</body>
</html>
