/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class DBUtils implements Serializable{
    public static Connection makeConnection() {
        try {
            //1. Load Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2. Tạo connection string - protocol:servertype://IPaddress:port;DBName;InstanceName
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=PRJ301_MVC2;"
                    + "instanceName=TIENHUYNHTN";
            //3. Lấy connection
            Connection con = DriverManager.getConnection(url, "sa", "Thuytien");
            //4. Trả connection cho đối tượng gọi
            return con;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
