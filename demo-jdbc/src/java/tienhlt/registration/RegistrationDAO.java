/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tienhlt.utils.DBUtils;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
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
        
        return false;
    }
}
