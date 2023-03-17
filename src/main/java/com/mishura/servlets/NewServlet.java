package com.mishura.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NewServlet", value = "/new")
public class NewServlet extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("Create a new one something!");
        final Object o = new Object();
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");
        responseBody.println(String.format("<h1 align=\"center\">HashCode: %s</h1>", o.hashCode()));
    }
}