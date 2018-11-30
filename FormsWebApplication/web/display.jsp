<%-- 
    Document   : display
    Created on : Oct 3, 2016, 4:26:01 AM
    Author     : Rakesh J
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form 2 </title>
    </head>
    <body>
        <h1>Display Form</h1>
        
        <%
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailID = request.getParameter("emailID");
            String gender = request.getParameter("gender");
            String DOB = request.getParameter("dob");
        %>
        
        <table border="1">
            
            <tbody>
                <tr>
                    <td>First Name:</td>
                    <td><%= firstName %></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><%= lastName %></td>
                </tr>
                <tr>
                    <td>Email ID:</td>
                    <td><%= emailID %></td>
                </tr>
                <tr>
                    <td>Gender:</td>
                    <td><%= gender %></td>
                </tr>
                <tr>
                    <td>DOB:</td>
                    <td><%= DOB %></td>
                </tr>
            </tbody>
        </table>

        
    </body>
</html>
