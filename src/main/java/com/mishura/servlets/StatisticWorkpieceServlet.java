package com.mishura.servlets;

import com.mishura.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "stats", value = "/stats/{id}")
public class StatisticWorkpieceServlet extends HttpServlet {
    @Override
    public void init() {
        final Controller controller = Controller.getInstance();
        System.out.println(getServletName() + " initialized");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Method GET from AddServlet");
    }

}