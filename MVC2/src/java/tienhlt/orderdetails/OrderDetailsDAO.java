/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.orderdetails;

import java.io.Serializable;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import tienhlt.utils.DBHelpers;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class OrderDetailsDAO implements Serializable{
    private List<OrderDetailsDTO> orderDetailsList;

    public List<OrderDetailsDTO> getOrderDetailsList() {
        return orderDetailsList;
    }
    
    public boolean inserItemsToOrderDetails(int orderID, List<OrderDetailsDTO> listOrderDetailsDTOs) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = "Insert Into OrderDetails"
                        + "(OrderID, SKU, Name, Price, Quantity, Total) "
                        + "Values (?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                
                int affectedRow = 0;
                for (OrderDetailsDTO item : listOrderDetailsDTOs) {
                    stm.setInt(1, orderID);
                    stm.setString(2, item.getSKU());
                    stm.setString(3, item.getName());
                    stm.setBigDecimal(4, item.getPrice());
                    stm.setInt(5, item.getQuantity());
                    stm.setBigDecimal(6, item.getTotal());
                    affectedRow += stm.executeUpdate();
                }
                
                con.commit();
                
                if (affectedRow == listOrderDetailsDTOs.size()) {
                    return true;
                }
            }
        } catch (SQLException ex){
            if (con != null) {
                con.rollback();
                log("OrderDetailsDAO_SQL: " + ex.getMessage());
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
