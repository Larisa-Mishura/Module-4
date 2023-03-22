package com.mishura.servlets;

import com.mishura.model.Detail;
import com.mishura.service.Service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StatisticWorkpieceServlet", value = "/stats/id")
public class StatisticsDetailServlet extends HttpServlet {

    private final Service service = Service.getInstance();

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");
        String id = request.getParameter("id");

        if(!service.getById(id).isPresent()){
            responseBody.println("<h2 align=\"center\">No detail present</h2>");
        } else {
            Detail detail = service.getById(id).get();
            responseBody.println(String.format("<h2 align=\"center\">ID - %s</h2>", id));
            responseBody.println(String.format("<h2 align=\"center\">Date - %s</h2>", detail.getCreated()));
            responseBody.println(String.format("<h2 align=\"center\">Spent time  - %s</h2>", detail.getSeconds()));
            responseBody.println(String.format("<h2 align=\"center\">Total amount of spent fuel - %s</h2>", detail.getSpentFuel()));
            responseBody.println(String.format("<h2 align=\"center\">Total amount of produced fuel - %s</h2>", detail.getProducedFuel()));
            responseBody.println(String.format("<h2 align=\"center\">Total amount of broken chips - %s</h2>", detail.getCountOfBrokenChips()));
        }
        responseBody.println("<h3></h3>");
        responseBody.println("<button align=\"center\" onclick=\"location.href='/'\">Back to main</button>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}