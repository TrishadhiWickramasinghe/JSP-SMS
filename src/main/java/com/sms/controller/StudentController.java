

import java.io.IOException;
import java.sql.SQLException;

import com.sms.model.Student;
import com.sms.service.CourseService;
import com.sms.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students")
public class StudentController extends HttpServlet {
    private StudentService studentService;
    private CourseService courseService;
    
    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        courseService = new CourseService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action != null ? action : "list") {
                case "list":
                    listStudents(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
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
                    addStudent(request, response);
                    break;
                case "update":
                    updateStudent(request, response);
                    break;
                default:
                    response.sendRedirect("students");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
    
    private void listStudents(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        request.setAttribute("students", studentService.getAllStudents());
        request.getRequestDispatcher("/student/list.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        request.setAttribute("courses", courseService.getAllCourses());
        request.getRequestDispatcher("/student/add.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.getStudentById(studentId);
        request.setAttribute("student", student);
        request.setAttribute("courses", courseService.getAllCourses());
        request.getRequestDispatcher("/student/edit.jsp").forward(request, response);
    }
    
    private void addStudent(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Student student = new Student();
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setEmail(request.getParameter("email"));
        student.setPhoneNumber(request.getParameter("phoneNumber"));
        student.setCourseId(Integer.parseInt(request.getParameter("courseId")));
        
        if (studentService.addStudent(student)) {
            response.sendRedirect("students?message=Student added successfully");
        } else {
            response.sendRedirect("students?error=Failed to add student");
        }
    }
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Student student = new Student();
        student.setStudentId(Integer.parseInt(request.getParameter("studentId")));
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setEmail(request.getParameter("email"));
        student.setPhoneNumber(request.getParameter("phoneNumber"));
        student.setCourseId(Integer.parseInt(request.getParameter("courseId")));
        
        if (studentService.updateStudent(student)) {
            response.sendRedirect("students?message=Student updated successfully");
        } else {
            response.sendRedirect("students?error=Failed to update student");
        }
    }
    
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        
        if (studentService.deleteStudent(studentId)) {
            response.sendRedirect("students?message=Student deleted successfully");
        } else {
            response.sendRedirect("students?error=Failed to delete student");
        }
    }
}