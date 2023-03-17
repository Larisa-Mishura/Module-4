package com.mishura.servlets;


import com.mishura.controller.Controller;
import com.mishura.util.Factory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "start", value = "/start")
public class MakeWorkpieceServlet extends HttpServlet {

    @Override
    public void init() {
        final Controller controller = Controller.getInstance();
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/start.jsp");
        requestDispatcher.forward(request, response);
        /*PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");
        responseBody.println("<form method = \"post\">");
        responseBody.println("<p>Do you want to make a new workpiece?</p>");
        responseBody.println("<button type=\"submit\">Submit</button>");
        responseBody.println("</form>");*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Factory.makeWorkpiece();
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");
        responseBody.println("<h3 align=\"center\">A new workpiece is made.</h3>");

        responseBody.println("<p><a href=\"add\">ADD</a></p>");
        responseBody.println("<p><a href=\"list\">LIST</a></p>");

    }
}