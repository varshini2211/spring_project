<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Employee List</h1>

    <!-- Display success or error message -->
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Department</th>
            <th>Salary</th>
            <th>Date of Joining</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td><c:out value="${employee.empId}"/></td>
                <td><c:out value="${employee.firstname}"/></td>
                <td><c:out value="${employee.lastname}"/></td>
                <td><c:out value="${employee.department}"/></td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.dateOfJoining}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/employee/${employee.empId}" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="submit" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="<c:url value='/employee/add'/>">Add New Employee</a>
</body>
</html>
