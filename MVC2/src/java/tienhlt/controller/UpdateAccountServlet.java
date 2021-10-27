/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tienhlt.registration.RegistrationCreateError;
import tienhlt.registration.RegistrationDAO;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.html";

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
        
        String searchValue = request.getParameter("lastSearchValue");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String checkAdmin = request.getParameter("chkAdmin");
        boolean role = false;
        if (checkAdmin != null) {
            role = true;
        }
        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        
        String url = ERROR_PAGE;
        
        try {
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthViolent("Password requires from 6 to 20 chars");
            }
            if (foundErr) {
                request.setAttribute("UPDATE_ERR", errors);
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
                return;
            }
            password = password.trim();
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateAccount(username, password, role);
            
            if (result) {
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }
            
        } catch (NamingException ex) {
            log("UpdateAccountServlet_SQL: " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateAccountServlet_Naming: " + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
