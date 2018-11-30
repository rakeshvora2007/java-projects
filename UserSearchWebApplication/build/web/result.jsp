<%-- 
    Document   : result.jsp
    Created on : Oct 4, 2016, 10:13:55 PM
    Author     : Rakesh J
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% ResultSet r =  (ResultSet)request.getAttribute("myResult")%>
        
            <table border="1">
                
                        <tr>
                            <td>First Name</td>
                            <td>Last Name</td>
                            <td>Title</td>                                  
                        </tr>
                    <%while (r.next()){%>
                    <tr>
                        <td><%= r.getString("first_name") %></td>
                        <td><%= r.getString("last_name") %></td>
                        <td><%= r.getString("title") %></td>
                    </tr>
                        <% } %>
                
            </table>
                
                
    </body>
</html>
