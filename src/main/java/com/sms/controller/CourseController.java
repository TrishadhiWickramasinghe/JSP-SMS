

import java.io.IOException;
import java.sql.SQLException;

import com.sms.model.Course;
import com.sms.service.CourseService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/courses")
public class CourseController extends HttpServlet {
    private CourseService courseService;
    
    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action != null ? action : "list") {
                case "list":
                    listCourses(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "delete":
                    deleteCourse(request, response);
                    break;
                default:
                    listCourses(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action != null ? action : "") {
                case "add":
                    addCourse(request, response);
                    break;
                default:
                    response.sendRedirect("courses");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
    
    private void listCourses(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        request.setAttribute("courses", courseService.getAllCourses());
        request.getRequestDispatcher("/course/list.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/course/add.jsp").forward(request, response);
    }
    
    private void addCourse(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Course course = new Course();
        course.setCourseName(request.getParameter("courseName"));
        course.setCourseCode(request.getParameter("courseCode"));
        course.setDescription(request.getParameter("description"));
        course.setCredits(Integer.parseInt(request.getParameter("credits")));
        
        if (courseService.addCourse(course)) {
            response.sendRedirect("courses?message=Course added successfully");
        } else {
            response.sendRedirect("courses?error=Failed to add course");
        }
    }
    
    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        
        if (courseService.deleteCourse(courseId)) {
            response.sendRedirect("courses?message=Course deleted successfully");
        } else {
            response.sendRedirect("courses?error=Failed to delete course");
        }
    }
}