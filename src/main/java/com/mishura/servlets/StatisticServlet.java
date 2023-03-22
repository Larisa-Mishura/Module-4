package com.mishura.servlets;

import com.mishura.DTO.StatisticsDTO;
import com.mishura.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StatisticServlet", value = "/stats")
public class StatisticServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");

       String id = request.getParameter("id");

        if(id != null){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/stats/id");
            requestDispatcher.forward(request, response);
        }

        Service service = Service.getInstance();
        StatisticsDTO statistics = service.getStatistics().get(0);
        responseBody.println(String.format("<h2>Total count of items - %s</h2>", statistics.getCount()));
        responseBody.println(String.format("<h2>Total amount of produced fuel - %s</h2>", statistics.getProducedFuel()));
        responseBody.println(String.format("<h2>Total amount of spent fuel - %s</h2>", statistics.getSpentFuel()));
        responseBody.println(String.format("<h2>Total amount of broken chips - %s</h2>", statistics.getCountOfBrokenChips()));


        responseBody.println("<h2>For more detailed information, enter ID of detail</h2>");
        responseBody.println("<form method = \"get\">");
        responseBody.println("<h2> Enter ID </h2>");
        responseBody.println("<label>ID:");
        responseBody.println("<input type=\"id\" name=\"id\"><br />");
        responseBody.println("<label>");
        responseBody.println("<button type=\"submit\">Submit</button>");
        responseBody.println("</form>");

        responseBody.println("<div>");
        responseBody.println("<button align=\"center\" onclick=\"location.href='/'\">Back to main</button>");
        responseBody.println("<div>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}