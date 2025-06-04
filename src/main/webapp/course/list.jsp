<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Course List</title>
</head>
<body>
    <h1>Course Management</h1>
    <a href="index.jsp">Home</a> | <a href="students">Manage Students</a>
    
    <h2>All Courses</h2>
    
    <!-- Display messages -->
    <c:if test="${param.message != null}">
        <div style="color: green; font-weight: bold;">${param.message}</div>
    </c:if>
    <c:if test="${param.error != null}">
        <div style="color: red; font-weight: bold;">${param.error}</div>
    </c:if>
    
    <p><a href="courses?action=add">Add New Course</a></p>
    
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Course Name</th>
                <th>Course Code</th>
                <th>Description</th>
                <th>Credits</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>${course.courseId}</td>
                    <td>${course.courseName}</td>
                    <td>${course.courseCode}</td>
                    <td>${course.description}</td>
                    <td>${course.credits}</td>
                    <td>
                        <a href="courses?action=delete&id=${course.courseId}" 
                           onclick="return confirm('Are you sure you want to delete this course?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty courses}">
                <tr>
                    <td colspan="6">No courses found. <a href="courses?action=add">Add the first course</a></td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>