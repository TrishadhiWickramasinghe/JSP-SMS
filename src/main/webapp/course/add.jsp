<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Course</title>
</head>
<body>
    <h1>Add New Course</h1>
    <a href="courses">Back to Course List</a>
    
    <form method="post" action="courses">
        <input type="hidden" name="action" value="add">
        
        <table>
            <tr>
                <td><label for="courseName">Course Name:</label></td>
                <td><input type="text" id="courseName" name="courseName" required></td>
            </tr>
            <tr>
                <td><label for="courseCode">Course Code:</label></td>
                <td><input type="text" id="courseCode" name="courseCode" required></td>
            </tr>
            <tr>
                <td><label for="description">Description:</label></td>
                <td><textarea id="description" name="description" rows="4" cols="30"></textarea></td>
            </tr>
            <tr>
                <td><label for="credits">Credits:</label></td>
                <td><input type="number" id="credits" name="credits" min="1" max="10" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Add Course">
                    <input type="reset" value="Reset">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>