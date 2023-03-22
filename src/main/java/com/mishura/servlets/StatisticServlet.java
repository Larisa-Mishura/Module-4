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
import java.util.List;

@WebServlet(name = "StatisticServlet", value = "/stats")
public class StatisticServlet extends HttpServlet {

    Service service = Service.getInstance();
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

        if(service.getStatistics().isEmpty()){
            responseBody.println("<h2>No detail present</h2>");
            return;
        }

        StatisticsDTO statistics = service.getStatistics().get(0);

        responseBody.println("<h1>INFORMATION ABOUT FACTORY</h1>");
        responseBody.println(String.format("<h2>Total amount of details - %s</h2>", statistics.getCount()));
        responseBody.println(String.format("<h2>Total amount of produced fuel - %s</h2>", statistics.getProducedFuel()));
        responseBody.println(String.format("<h2>Total amount of spent fuel - %s</h2>", statistics.getSpentFuel()));
        responseBody.println(String.format("<h2>Total amount of broken chips - %s</h2>", statistics.getCountOfBrokenChips()));
        responseBody.print("<br>");

        responseBody.println("<h2>For more detailed information, select ID of detail:</h2>");

        responseBody.println("<form method = \"get\">");
        List<String> list = service.getDetailsId();
        responseBody.println("ID: <select name=\"id\">");
        for(String option : list){
            responseBody.println(String.format("<option>%s</option>", option));
        }
        responseBody.println("</select>");
        responseBody.println("<button type=\"submit\">Submit</button>");
        responseBody.println("</form>");
        responseBody.print("<br>");
        responseBody.println("<button align=\"center\" onclick=\"location.href='/'\">Back to main</button>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}