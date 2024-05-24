package com.example.school.service;

import com.example.school.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setAge(resultSet.getInt("age"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public double getAverageAge() {
        double averageAge = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT AVG(age) AS avg_age FROM students")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                averageAge = resultSet.getDouble("avg_age");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return averageAge;
    }

    public List<Student> getStudentsAboveAverageAge() {
        List<Student> students = getAllStudents();
        double averageAge = getAverageAge();
        List<Student> aboveAverageStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() > averageAge) {
                aboveAverageStudents.add(student);
            }
        }
        return aboveAverageStudents;
    }
}
