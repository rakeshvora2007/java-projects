
<%-- 
    Document   : newjspsearchResult
    Created on : Oct 3, 2016, 9:52:14 PM
    Author     : Rakesh J
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*;" %>
<% Class.forName("com.mysql.jdbc.Driver");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h1>Your result for your favorite actor</h1>
       
        
        <%!
            public class search
            {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String USERNAME = "root";
                String PASSWORD = "password";
                
                Connection con = null;
                PreparedStatement ps1 = null;
                ResultSet rs =  null;
                
                
                public search()
                {
                    try
                        {
                            con = DriverManager.getConnection(url, USERNAME, PASSWORD);
                            ps1 = con.prepareStatement( "SELECT a.first_name, a.last_name, c.title FROM actor a, film_actor b, film c WHERE a.first_name = ? AND a.last_name = ? AND a.actor_id = b.actor_id AND b.film_id = c.film_id");
                            
                        }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
                
                public ResultSet getSearchResult(String firstName, String lastName)
                {
                    try
                    {   ps1.setString(1, firstName);
                        ps1.setString(2, lastName);
                        rs = ps1.executeQuery();                                        
                    }

                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    return rs;
                }
            }
            
        %>
        
        <%
            String firstName = request.getParameter("first");
            String lastName = request.getParameter("last");
            
            search s = new search();
            ResultSet r = s.getSearchResult(firstName, lastName);
        %>
            <table border="1">
                <tbody>
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
                </tbody>
            </table>

    </body>
</html>
