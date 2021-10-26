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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienhlt.registration.RegistrationDAO;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "StartupApplicationServlet", urlPatterns = {"/StartupApplicationServlet"})
public class StartupApplicationServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

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
        
        String url = LOGIN_PAGE;
        
        
        try {
//            //1. Read cookies
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                //2. Get last cookies
//                Cookie lastCookie = cookies[cookies.length - 1];
//                //3. Get username and password
//                String username = lastCookie.getName();
//                String password = lastCookie.getValue(); //decrypt or find hash key
//                //4. Call DAO to checkLogin
//                RegistrationDAO dao = new RegistrationDAO();
//                boolean result = dao.checkLogin(username, password);
//                
//                if (result) {
//                    url = SEARCH_PAGE;
//                }//end if authentication is ok
//            } //end if cookies has existed
            
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String username = cookie.getName();
                    String password = cookie.getValue();
                    
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.checkLogin(username, password);

                    if (result) {
                        url = SEARCH_PAGE;
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", username);

                        String seusername = (String)session.getAttribute("USER");
                        System.out.println(seusername + " startup");
                    }//end if authentication is ok
                }//end for
            }//end if cookies has existed
        } catch (SQLException ex) {
            log("StartupApplicationServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("StartupApplicationServlet_Naming: " + ex.getMessage());
        } finally {
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
