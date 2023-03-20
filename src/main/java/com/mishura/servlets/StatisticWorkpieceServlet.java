package com.mishura.servlets;

import com.mishura.model.Workpiece;
import com.mishura.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "stats", value = "/stats/{id}")
public class StatisticWorkpieceServlet extends HttpServlet {

    private final Service service = Service.getInstance();

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");
        String workpieceId = request.getParameter("id");
        Workpiece workpiece = service.getById(workpieceId).get();
        responseBody.println("<h3>" + workpiece.toString() + " </h3>");
        responseBody.println("<p><a href=\"add\">ADD</a></p>");
        responseBody.println("<p><a href=\"list\">LIST</a></p>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}