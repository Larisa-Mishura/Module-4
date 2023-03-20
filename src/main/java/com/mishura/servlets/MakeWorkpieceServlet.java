package com.mishura.servlets;


import com.mishura.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "start", value = "/start")
public class MakeWorkpieceServlet extends HttpServlet {

    private final Service service = Service.getInstance();

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/start.jsp");
        requestDispatcher.forward(request, response);*/
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");
        responseBody.println("<form method = \"post\">");
        responseBody.println("<p>Do you want to make a new workpiece?</p>");
        responseBody.println("<button align=\"center\"type=\"submit\">Submit</button>");
        responseBody.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = service.createAndSave();
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");

        responseBody.println("<h3 align=\"center\">A new workpiece is made:</h3>");
        responseBody.println("<h3 align=\"center\">id " + id + "</h3>");

        responseBody.println("<button onclick=\"location.href='/stats'\">Statistics</button>");
        responseBody.println("<button onclick=\"location.href='/start'\">Make new workpiece</button>");

    }
}