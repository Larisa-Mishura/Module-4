package com.mishura.servlets;

import com.mishura.service.Service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "start", value = "/start")
public class MakeDetailServlet extends HttpServlet {

    private final static Service service = Service.getInstance();

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");

        responseBody.println("<a><img src=\"robot2.jpg\" alt=\"\"></a>");

        responseBody.println("<form method = \"post\">");
        responseBody.println("<h2>Do you want to make a new detail?</h2>");
        responseBody.println("<button type=\"submit\">Submit</button>");
        responseBody.println("</form>");
        responseBody.println("<button align=\"center\" onclick=\"location.href='/'\">Back to main</button>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String id = service.createAndSave();

        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");

        responseBody.println("<a><img src=\"robot2.jpg\" alt=\"\"></a>");

        responseBody.println("<h3>A new detail has been made:</h3>");
        responseBody.println("<h3>id " + id + "</h3>");
        responseBody.print("<br>");
        responseBody.println("<button align=\"center\" onclick=\"location.href='/'\">Back to main</button>");
    }
}