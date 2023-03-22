package com.mishura.servlets;

import com.mishura.model.Detail;
import com.mishura.service.Service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "StatisticWorkpieceServlet", value = "/stats/id")
public class StatisticsDetailServlet extends HttpServlet {

    private final static Service service = Service.getInstance();

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");
        String id = request.getParameter("id");

        Detail detail = service.getById(id).get();
        responseBody.println("<h1>INFORMATION ABOUT DETAIL</h1>");
        responseBody.println(String.format("<h2>ID - %s</h2>", id));
        responseBody.println(String.format("<h2>Date of creating - %s</h2>", FORMATTER.format(detail.getCreated())));
        responseBody.println(String.format("<h2>Spent time  - %s seconds</h2>", detail.getSeconds()));
        responseBody.println(String.format("<h2>Total amount of spent fuel - %s</h2>", detail.getSpentFuel()));
        responseBody.println(String.format("<h2>Total amount of produced fuel - %s</h2>", detail.getProducedFuel()));
        responseBody.println(String.format("<h2>Total amount of broken chips - %s</h2>", detail.getCountOfBrokenChips()));
        responseBody.print("<br>");
        responseBody.println("<button onclick=\"location.href='/'\">Back to main</button>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}