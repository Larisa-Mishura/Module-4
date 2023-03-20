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

        responseBody.println("<h3 Total amount of produced fuel - :</h3>");
        responseBody.println("<h3>" + statistics.getProducedFuel() + " </h3>");

        responseBody.println("<h3 Total amount of spent fuel - :</h3>");
        responseBody.println("<h3>" + statistics.getSpentFuel() + " </h3>");

        responseBody.println("<h3 Total amount of broken chips - :</h3>");
        responseBody.println("<h3>" + statistics.getCountOfBrokenChips() + " </h3>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}