/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienhlt.registration.RegistrationDAO;
import tienhlt.registration.RegistrationDTO;
import tienhlt.registration.RegistrationUpdateError;
import tienhlt.utils.MyApplicationConstant;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        String searchValue = request.getParameter("lastSearchValue");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String checkAdmin = request.getParameter("chkAdmin");
        boolean role = false;
        if (checkAdmin != null) {
            role = true;
        }
        boolean foundErr = false;
        RegistrationUpdateError errors = new RegistrationUpdateError();
        
        ServletContext context = this.getServletContext();
        Properties properties = (Properties)context.getAttribute("SITE_MAP");
        
        String url = properties.getProperty(
                        MyApplicationConstant.UpdateFeatures.ERROR_PAGE);
        
        try {
            HttpSession session = request.getSession(false);
            
            if (session == null) {
                url = properties.getProperty(
                        MyApplicationConstant.UpdateFeatures.LOGIN_PAGE);
                return;
            }
            
            if (!password.trim().matches(properties.getProperty(
                    MyApplicationConstant.UpdateFeatures.PASSWORD_REGEX))) {
                foundErr = true;
                errors.setPasswordViolent(properties.getProperty(
                    MyApplicationConstant.UpdateFeatures.PASSWORD_VIOLENT_NOTICE));
            }
            
            if (foundErr) {
                request.setAttribute("UPDATE_ERR", errors);
//                url = "DispatchServlet"
//                        + "?btAction=Search"
//                        + "&txtSearchValue=" + searchValue;
                url = properties.getProperty(
                        MyApplicationConstant.DeleteFeatures.SEARCH_FULLNAME_CONTROLLER) 
                        + "?txtSearchValue=" + searchValue;
                return;
            }
            
            password = password.trim();
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateAccount(username, password, role);
            
            if (result) {
                if (username.equals(session.getAttribute("USER"))) {
                    if (role == false) {
                        session.setAttribute("ADMIN", role);
                        RegistrationDTO dto = dao.showProfile(username);
                        session.setAttribute("SHOW_PROFILE", dto);
                    }
                }
//                url = "DispatchServlet"
//                        + "?btAction=Search"
//                        + "&txtSearchValue=" + searchValue;
                url = properties.getProperty(
                        MyApplicationConstant.DeleteFeatures.SEARCH_FULLNAME_CONTROLLER) 
                        + "?txtSearchValue=" + searchValue;
            }
            
        } catch (NamingException ex) {
            log("UpdateAccountServlet_SQL: " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateAccountServlet_Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
