<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Details</title>
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
    <h1>Employee Details</h1>
    
    <c:choose>
        <c:when test="${not empty employee}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Department</th>
                    <th>Salary</th>
                    <th>Date of Joining</th>
                </tr>
                <tr>
                    <td><c:out value="${employee.empId}"/></td>
                    <td><c:out value="${employee.firstname}"/></td>
                    <td><c:out value="${employee.lastname}"/></td>
                    <td><c:out value="${employee.department}"/></td>
                    <td><c:out value="${employee.salary}"/></td>
                    <td><c:out value="${employee.dateOfJoining}"/></td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <p>${error}</p>
        </c:otherwise>
    </c:choose>
    
</body>
</html>
