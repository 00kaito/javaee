package com.jstart.servlets;

import com.jstart.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/UserSessionServlet")
public class UserSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(10); //10s
        request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = createUser(request);
        }
        if (user != null) {
            session.setAttribute("user", user);
        }
        sendResponse(response, user);
    }

    private void sendResponse(HttpServletResponse response, User user) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print("<html>");
        writer.print("<body>");
        writer.print("<h1>Test sesji</h1>");
        if (user != null && user.getFirstName() != null && user.getLastName() != null)
            writer.println("<div>Witaj " + user.getFirstName() + " " + user.getLastName() + "</div>");
        else
            writer.println("<div>Witaj nieznajomy</div>");
        writer.println("  </body>");
        writer.println("</html>");
    }

    private User createUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        if (firstName == null || lastName == null) {
            return null;
        } else {
            return new User(firstName, lastName);
        }
    }
}
