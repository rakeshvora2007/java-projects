<%-- 
    Document   : index
    Created on : Oct 3, 2016, 9:44:52 PM
    Author     : Rakesh J
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Your Favorite Actor Movie List </title>
    </head>
    <body>
        <h1>Enter Search for your favorite movie actor to enlist all the movie he has acted in.</h1>
        
        <form name="userRequestForm" action="newjspsearchResult.jsp" method="POST">
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
        </form>
    </body>
</html> 
