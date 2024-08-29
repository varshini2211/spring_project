<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
    <h1>Add New Employee</h1>

    <form action="${pageContext.request.contextPath}/employee/insert" method="post">
        <label for="empId">ID:</label>
        <input type="text" id="empId" name="empId" required/><br/>

        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" required/><br/>

        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" required/><br/>

        <label for="department">Department:</label>
        <input type="text" id="department" name="department" required/><br/>

        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary" required/><br/>

        <label for="dateOfJoining">Date of Joining:</label>
        <input type="text" id="dateOfJoining" name="dateOfJoining" required/><br/>

        <input type="submit" value="Add Employee"/>
    </form>

    <a href="<c:url value='/employee/'/>">Back to Employee List</a>
</body>
</html>
