<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%!
    String login;
    String time = "";
    List<String> conversation = new ArrayList<>();
%>
<%
    login = request.getParameter("login");
    time = request.getParameter("time");

    if (session.getAttribute("login") == null)
        session.setAttribute("login", login);

    if (request.getParameter("message") == null || "".equals(request.getParameter("message"))) {
        request.setAttribute("message", "");
    }else{
        conversation.add(Calendar.getInstance().getTime().getHours() + ":"
                + Calendar.getInstance().getTime().getMinutes() + ":"
                + Calendar.getInstance().getTime().getSeconds() + " "
                + session.getAttribute("login") + ": "
                + request.getParameter("message"));
    }

%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Witaj <%= session.getAttribute("login") %>
    </title>
</head>
<body>
<h2>Napisz wiadomosc:</h2>
<form action="chat.jsp" method="post">
    <input type="text" name="message">
    <input type="hidden" name="time">
    <input type="submit" value="Wyślij">
</form>
<h3>Zalogowany <%= session.getAttribute("login") %>
</h3>
<p>---------------------</p>

<% for (String str : conversation) { %>
<p><%= str %>
</p>

<% } %>
</body>
</html>