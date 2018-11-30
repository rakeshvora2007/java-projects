

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forms</title>
    </head>
    <body>
        <h1>Using Forms</h1>
        <form name="myForm" action="display.jsp" method="POST">
            
            <table border="0">
                
                <tbody>
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="fisrtName" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="lastName" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Email ID:</td>
                        <td><input type="text" name="emailID" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Gender:</td>
                        <td><select name="gender">
                                <option>Male</option>
                                <option>Female</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>DOB:</td>
                        <td><input type="text" name="dob" value="MM/DD/YYYY" size="50" /></td>
                    </tr>
                </tbody>
            </table>

            <input type="reset" value="clear" name="clear"/>
            <input type="submit" value="submit" name="submit" />
        </form>
        
    </body>
</html>
