<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
    <h1>Student Management</h1>
    <a href="index.jsp">Home</a> | <a href="courses">Manage Courses</a>
    
    <h2>All Students</h2>
    
    <!-- Display messages -->
    <c:if test="${param.message != null}">
        <div style="color: green; font-weight: bold;">${param.message}</div>
    </c:if>
    <c:if test="${param.error != null}">
        <div style="color: red; font-weight: bold;">${param.error}</div>
    </c:if>
    
    <p><a href="students?action=add">Add New Student</a></p>
    
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Course</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.studentId}</td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.email}</td>
                    <td>${student.phoneNumber}</td>
                    <td>${student.courseName}</td>
                    <td>
                        <a href="students?action=edit&id=${student.studentId}">Edit</a> |
                        <a href="students?action=delete&id=${student.studentId}" 
                           onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty students}">
                <tr>
                    <td colspan="7">No students found. <a href="students?action=add">Add the first student</a></td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>