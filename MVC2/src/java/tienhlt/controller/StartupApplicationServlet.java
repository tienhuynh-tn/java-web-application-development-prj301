/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienhlt.registration.RegistrationDAO;
import tienhlt.registration.RegistrationDTO;
import tienhlt.utils.MyApplicationConstant;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "StartupApplicationServlet", urlPatterns = {"/StartupApplicationServlet"})
public class StartupApplicationServlet extends HttpServlet {

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
        
        String url = MyApplicationConstant.StartUpApp.LOGIN_PAGE;
        
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String username = cookie.getName();
                    String password = cookie.getValue();
                    
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.checkLogin(username, password);

                    if (result) {
                        String fullname = dao.showFullName(username);
                        boolean isAdmin = dao.checkAdmin(username);
                        RegistrationDTO dto = dao.showProfile(username);
                        
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", username);
                        session.setAttribute("FULL_NAME", fullname);
                        
                        if (isAdmin) {
                            session.setAttribute("ADMIN", isAdmin);
                        } else {
                            session.setAttribute("ADMIN", isAdmin);
                            session.setAttribute("SHOW_PROFILE", dto);
                        }
                        
                        url = MyApplicationConstant.StartUpApp.SEARCH_PAGE;
                    }//end if authentication is ok
                }//end for
            }//end if cookies has existed
        } catch (SQLException ex) {
            log("StartupApplicationServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("StartupApplicationServlet_Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
