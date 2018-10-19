package com.jstart.servlets;

import com.jstart.model.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/UserDetailsServlet",
        initParams = {@WebInitParam(name = "defaultUsername", value = "undefined"),
                @WebInitParam(name = "defaultPassword", value = "undefined")})
public class UserDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDetails user = createUserFromRequest(request);
        if(user.getUsername()==null || "".equals(user.getUsername())){
            user.setUsername(getInitParameter("defaultUsername"));
        }
        if(user.getPassword()==null || "".equals(user.getPassword())){
            user.setPassword(getInitParameter("defaultPassword"));
        }
        sendResponse(user, response);
    }

    private void sendResponse(UserDetails user, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.print("<h2>Dane odebrano pomyślnie</h2>");
        writer.print("<div>");
        writer.println(user.getUsername() + "<br>");
        writer.println(user.getPassword() + "<br>");
        writer.println(user.getGender() + "<br>");
        if(user.getHobby() != null) {
            writer.print("Hobby: <br>");
            for(String hobby: user.getHobby())
                writer.println(" " + hobby + "<br>");
        }
        writer.print("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }

    private UserDetails createUserFromRequest(HttpServletRequest request){
        UserDetails user = new UserDetails();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("pass"));
        user.setGender(request.getParameter("gender"));
        user.setHobby(request.getParameterValues("hobby"));
        return user;
    }
}
