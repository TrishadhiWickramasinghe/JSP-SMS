<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
    <h1>Edit Student</h1>
    <a href="students">Back to Student List</a>
    
    <form method="post" action="students">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="studentId" value="${student.studentId}">
        
        <table>
            <tr>
                <td><label for="firstName">First Name:</label></td>
                <td><input type="text" id="firstName" name="firstName" value="${student.firstName}" required></td>
            </tr>
            <tr>
                <td><label for="lastName">Last Name:</label></td>
                <td><input type="text" id="lastName" name="lastName" value="${student.lastName}" required></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="email" id="email" name="email" value="${student.email}" required></td>
            </tr>
            <tr>
                <td><label for="phoneNumber">Phone Number:</label></td>
                <td><input type="text" id="phoneNumber" name="phoneNumber" value="${student.phoneNumber}" required></td>
            </tr>
            <tr>
                <td><label for="courseId">Course:</label></td>
                <td>
                    <select id="courseId" name="courseId" required>
                        <option value="">Select a Course</option>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.courseId}" 
                                    ${course.courseId == student.courseId ? 'selected' : ''}>
                                ${course.courseName} (${course.courseCode})
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Update Student">
                    <a href="students">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>