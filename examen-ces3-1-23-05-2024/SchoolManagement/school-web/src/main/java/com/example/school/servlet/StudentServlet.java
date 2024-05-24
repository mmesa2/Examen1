package com.example.school.servlet;

import com.example.school.model.Student;
import com.example.school.service.DatabaseService;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = databaseService.getAllStudents();
        String json = new Gson().toJson(students);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
