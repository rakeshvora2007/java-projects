


<%@page import="java.sql.*"%>
<% Class.forName("com.mysql.jdbc.Driver");%>>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>database Page</title>
    </head>
    <body>
        <h1>dataBase Example</h1>
        
        <%!
            public class Actor {
                String url = "jdbc:mysql://localhost:3306";
                String USERNAME = "root";
                String PASSWORD = "password";
                
                Connection connection = null;
                PreparedStatement pr = null;
                ResultSet resultset = null;
                
                public Actor()
                {
                    try{
                    connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
                    
                    pr = connection.prepareStatement("SELECT Name, Capital, population FROM world.country");
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
                }
                
                public ResultSet getActors()
                {
                try{
                    resultset = pr.executeQuery();
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                     }
                     return resultset;
                }

}
            %>
            
            <%
                
                Actor a = new Actor();
                ResultSet r = a.getActors();
                
            
            %>
            <table border="1">
              <tbody>
                    <tr>
                        <td>Name</td>
                        <td>Capital</td>
                        <td>population</td>
                    </tr>
                    <%while (r.next()){%>
                    <tr>
                        <td><%= r.getString("Name")%></td>
                        <td><%= r.getString("Capital")%></td>
                        <td><%= r.getString("population")%></td>
                    </tr>
                            <%}%>
                </tbody>
            </table>

    </body>
</html>
