package com.jstart.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        //request scope
        if(request.getAttribute("login") == null)
            request.setAttribute("login", login);
        //session scope
        if(request.getSession().getAttribute("login") == null)
            request.getSession().setAttribute("login", login);
        //servlet scope
        if (getServletContext().getAttribute("login") == null)
            getServletContext().setAttribute("login", login);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head><title>Witaj " + request.getAttribute("login") + "</title></head>");
        writer.println("<body>");
        writer.println("<h1>Rewuest: " + request.getAttribute("login") + "</h1>");
        writer.println("<h1>Session: " + request.getSession().getAttribute("login") + "</h1>");
        writer.println("<h1>Servlet scope: " + request.getServletContext().getAttribute("login") + "</h1>");
        writer.println("<p>odśwież i wyślij formularz ponownie </p>");
        writer.println("</body>");
        writer.println("</html>");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10); //10s

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
