package com.mishura.servlets;

import com.mishura.DTO.StatisticsDTO;
import com.mishura.model.Workpiece;
import com.mishura.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StatisticServlet", value = "/stats")
public class StatisticServlet extends HttpServlet {

    private final Service service = Service.getInstance();

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");
        StatisticsDTO statistics = service.getStatistics().get(0);

        responseBody.println(String.format("<h2 align=\"center\">Total amount of produced fuel - %s</h2>", statistics.getProducedFuel()));
        responseBody.println(String.format("<h2 align=\"center\">Total amount of spent fuel - %s</h2>", statistics.getSpentFuel()));
        responseBody.println(String.format("<h2 align=\"center\">Total amount of broken chips - %s</h2>", statistics.getCountOfBrokenChips()));

        responseBody.println("<h2 align=\"center\">For more detailed information, enter ID of workpiece</p>");
        responseBody.println("<form method = \"get\">");
        responseBody.println("<h3> Enter ID </h3>");
        responseBody.println("<label>ID:");
        responseBody.println("<input type=\"id\" name=\"id\"><br />");
        responseBody.println("<label>");
        responseBody.println("<button type=\"submit\">Submit</button>");
        responseBody.println("</form>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}