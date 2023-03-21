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

@WebServlet(name = "StatisticWorkpieceServlet", value = "/stats/id")
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

        responseBody.println(String.format("<h2 align=\"center\">ID - %s</h2>", workpieceId));
        responseBody.println(String.format("<h2 align=\"center\">Date - %s</h2>", workpiece.getCreated()));
        responseBody.println(String.format("<h2 align=\"center\">Spent time  - ", workpiece.getSeconds()));
        responseBody.println(String.format("<h2 align=\"center\">Total amount of spent fuel - %s</h2>", workpiece.getSpentFuel()));
        responseBody.println(String.format("<h2 align=\"center\">Total amount of produced fuel - %s</h2>", workpiece.getProducedFuel()));
        responseBody.println(String.format("<h2 align=\"center\">Total amount of broken chips - %s</h2>", workpiece.getCountOfBrokenChips()));

    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}