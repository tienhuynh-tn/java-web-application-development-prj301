/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tienhlt.utils.DBHelpers;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class OrdersDAO implements Serializable{
    public int createNewOrder(String name, String address, String total) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Orders(Name, Address, Total) "
                        + "Output inserted.OrderID "
                        + "Values (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, address);
                stm.setBigDecimal(3, new BigDecimal(total));
                rs = stm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    return orderID;
                }
                
            } //end if con connect success
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }
    
//    public boolean updateTotalOfOrder(int orderID, BigDecimal total) 
//        throws SQLException, NamingException{
//        Connection con = null;
//        PreparedStatement stm = null;
//        
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Update Orders "
//                        + "Set Total = ? "
//                        + "Where OrderID = ?";
//                stm = con.prepareStatement(sql);
//                stm.setBigDecimal(1, total);
//                stm.setInt(2, orderID);
//                int rowAffected = stm.executeUpdate();
//                if (rowAffected > 0) {
//                    return true;
//                }
//                
//            } //end if con connect success
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return false;
//    }
}
