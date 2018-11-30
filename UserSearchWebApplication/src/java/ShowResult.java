/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rakesh J
 */
public class ShowResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String firstName = "";
        String lastName = "";

        if (request.getParameter("first") != null) {
            firstName = request.getParameter("first");
        }
        if (request.getParameter("last") != null) {
            lastName = request.getParameter("last");
        }
        Search s;
        s = new Search();
        ResultSet r = s.getSearchResult(firstName, lastName);
        request.setAttribute("myResult", r);
        try {
            //response.sendRedirect("result.jsp");

            PrintWriter out = response.getWriter();
            out.println("<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>JSP Page</title>\n"
                    + "    </head>\n"
                    + "    <body>");
            out.println("<h1>Hello World!</h1>");
            out.println(" <table border=\"1\">\n"
                    + "                <tbody>\n"
                    + "                        <tr>\n"
                    + "                            <td>First Name</td>\n"
                    + "                            <td>Last Name</td>\n"
                    + "                            <td>Title</td>                                  \n"
                    + "                        </tr>");
            while (r.next()) {
                out.println("<tr>\n"
                        + "                        <td>" + r.getString("first_name") + "</td>\n"
                        + "                        <td>" + r.getString("last_name") + "</td>\n"
                        + "                        <td>" + r.getString("title") + "</td>\n"
                        + "                    </tr>");
            }
            out.println("              </tbody>\n"
                    + "            </table>\n"
                    + "                \n"
                    + "                \n"
                    + "    </body>\n"
                    + "</html>");
        } catch (SQLException ex) {
            Logger.getLogger(ShowResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
