<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.Food" %>

<!doctype html>
<html>
<head>
    <title>First JSP</title>
</head>
<body>
<h1>Hello JSP</h1>
<%
    response.getWriter().write("First message");
    out.print("right here, after header");
%>
<br/>
<%= request.getRequestURI() %>
<br>
<%= LocalDateTime.now() %>
<hr/>
<%
    List<Food> users = (List) request.getAttribute("food");
    Food anUser = users.get(0);
%>
    <b><%= anUser.getName() %></b>
    <i><%= anUser.getDate() %></i>
    <u><%= anUser.getPrice() %></u>

<table>
<% for (Food user : users) {%>
    <tr>
       <td><%= user.getName() %></td>
       <td>
           <% if (user.getName().equals("Vietnam")) { %>
                <b><%= user.getName() %></b>
           <% } else { %>
                <%= user.getName() %>
           <% } %>
       </td>
       <td><%= user.getPrice() %></td>
    </tr>
<% } %>
</table>
</body>
</html>
