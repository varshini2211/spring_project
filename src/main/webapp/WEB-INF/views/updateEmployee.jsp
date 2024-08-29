<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
    <h1>Update Employee</h1>
    
    <!-- Display success or error message -->
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/employee/update" method="post">
        <input type="hidden" name="empId" value="${employee.empId}" />
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" value="${employee.firstname}" required /><br>
        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" value="${employee.lastname}" required /><br>
        <label for="department">Department:</label>
        <input type="text" id="department" name="department" value="${employee.department}" required /><br>
        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary" value="${employee.salary}" required /><br>
        <label for="dateOfJoining">Date of Joining:</label>
        <input type="text" id="dateOfJoining" name="dateOfJoining" value="${employee.dateOfJoining}" required /><br>
        <input type="submit" value="Update Employee" />
    </form>

    <a href="${pageContext.request.contextPath}/employee/">Back to Employee List</a>
</body>
</html>
