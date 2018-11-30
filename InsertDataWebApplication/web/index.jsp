<%-- 
    Document   : index
    Created on : Oct 6, 2016, 7:03:08 PM
    Author     : Rakesh J
--%>
<%@page import="java.sql.*;" %>
<%@page import="java.util.Date;" %>
<% Class.forName("com.mysql.jdbc.Driver");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data Entry</title>
    </head>
    <body onLoad="displayResults()">
        <h1>Inserting Into Database</h1>

        <%!
            public class Actor {

                String url = "jdbc:mysql://localhost:3306/sakila";
                String USERNAME = "root";
                String PASSWORD = "password";
                Connection con = null;
                PreparedStatement ps = null;

                public Actor() {

                    try {
                        con = DriverManager.getConnection(url, USERNAME, PASSWORD);
                        ps = con.prepareStatement("INSERT into actor(first_name, last_name, last_update) VALUES (?, ?, ?)");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                public int setActors(String first, String last, Timestamp timeStamp) {

                    int result = 0;

                    try {
                        ps.setString(1, first);
                        ps.setString(2, last);
                        ps.setTimestamp(3, timeStamp);

                        result = ps.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return result;
                }

            }
        %>

        <%
            int result = 0;

            if (request.getParameter("submit") != null) {

                String firstName = new String();
                String lastName = new String();

                if (request.getParameter("first") != null) {
                    firstName = request.getParameter("first");
                }

                if (request.getParameter("last") != null) {
                    lastName = request.getParameter("last");
                }
                Date date = new Date();
                Timestamp timeStamp = new Timestamp(date.getTime());

                Actor a = new Actor();
                a.setActors(firstName, lastName, timeStamp);
                
                result++;    
            }
        %>


        <form name="myForm" action="index.jsp" method="POST">

            <table>

                <tbody>
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="first" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="last" value="" size="50" /></td>
                    </tr>
                </tbody>
            </table>
            
            <input type="reset" value="clear" name="clear" />
            <input type="submit" value="submit" name="submit" />
            <input type="hidden" name="hidden" value=<%= result %> />

        </form>
            
        <SCRIPT LANGUAGE = "JavaScript">
            
            function displayResults()
            
            {
                if(document.myForm.hidden.value == 1)
                {
                    alert("Data Inserted");
                }
            }
            
            </SCRIPT>


    </body>
</html>
