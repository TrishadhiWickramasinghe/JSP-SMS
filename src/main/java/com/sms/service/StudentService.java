package com.sms.service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sms.connection.DatabaseConnection;
import com.sms.model.Student;

public class StudentService {
    
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.student_id, s.first_name, s.last_name, s.email, s.phone_number, " +
                    "s.course_id, c.course_name FROM students s " +
                    "LEFT JOIN courses c ON s.course_id = c.course_id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setCourseId(rs.getInt("course_id"));
                student.setCourseName(rs.getString("course_name"));
                students.add(student);
            }
        }
        return students;
    }
    
    public Student getStudentById(int studentId) throws SQLException {
        String sql = "SELECT s.student_id, s.first_name, s.last_name, s.email, s.phone_number, " +
                    "s.course_id, c.course_name FROM students s " +
                    "LEFT JOIN courses c ON s.course_id = c.course_id WHERE s.student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setEmail(rs.getString("email"));
                    student.setPhoneNumber(rs.getString("phone_number"));
                    student.setCourseId(rs.getInt("course_id"));
                    student.setCourseName(rs.getString("course_name"));
                    return student;
                }
            }
        }
        return null;
    }
    
    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name, email, phone_number, course_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhoneNumber());
            pstmt.setInt(5, student.getCourseId());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, phone_number = ?, course_id = ? WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhoneNumber());
            pstmt.setInt(5, student.getCourseId());
            pstmt.setInt(6, student.getStudentId());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            return pstmt.executeUpdate() > 0;
        }
    }
}