<%@ page import="com.example.sample.LoginBean" %>
<%@ page import="java.sql.SQLException" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
        LoginBean login = new LoginBean();
        String errorMessage = null;

        try {
            if (login.validate(username.trim(), password.trim())) {
                response.sendRedirect("/Sample/LoginSignup/welcome.html"); // Ensure path is correct
            } else {
                errorMessage = "Invalid username or password";
            }
        } catch(SQLException e) {
            errorMessage = "Database error occurred: " + e.getMessage();
        }

        if(errorMessage != null) {
%>
        <p style="color: red;"><%= errorMessage %></p>
<%
        }
    } else {
        // Optional: you can add a check to display an error if username or password is empty.
        out.println("<p style='color: red;'>Please enter both username and password.</p>");
    }
%>
