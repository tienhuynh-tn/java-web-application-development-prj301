/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienhlt.cart.CartObject;
import tienhlt.product.ProductDTO;
import tienhlt.utils.MyApplicationConstant;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "RemoveBookFromCartServlet", urlPatterns = {"/RemoveBookFromCartServlet"})
public class RemoveBookFromCartServlet extends HttpServlet {

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
        
        ServletContext context = this.getServletContext();
        Properties properties = (Properties)context.getAttribute("SITE_MAP");
        
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession(false); 
            //Để đảm bảo rằng vùng nhớ session còn tồn tại ở server
            //Sẽ có trường hợp, giao diện view ở client vẫn còn, nhưng session không còn do time-out
            //=> check false, nếu có session thì remove, không có thì nghỉ
            //Không check true vì nếu không có thì tạo mới làm chi???
            //Muốn remove được thì session phải tồn tại
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets all items
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust chooses removing items
                        String[] selectedItem = request.getParameterValues("chkItem");
                        if (selectedItem != null) {
                            //5. remove all selected items from cart
                            for (String item : selectedItem) {
                                cart.removeItemFromCart(item);
                            }
                            //6. update cart to cart place
                            session.setAttribute("CART", cart);
                        }
                    }
                }//end cart has existed
            }//session is existed
        } catch (SQLException ex){
            log("RemoveBookFromCartServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("RemoveBookFromCartServlet_Naming: " + ex.getMessage());
        } finally {
            //7. Refresh viewing cart --> call view cart function again
//            String urlWriting = "DispatchServlet"
//                    + "?btAction=Buy";
//            String urlWriting = "DispatchServlet"
//                    + "?btAction=View Your Cart";
            String url = properties.getProperty(
                                    MyApplicationConstant.RemoveBookFeatures.VIEW_CART_PAGE);
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
