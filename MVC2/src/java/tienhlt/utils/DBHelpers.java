/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
 */
public class DBHelpers implements Serializable {
    public static Connection makeConnection() 
        throws /*ClassNotFoundException, SQLException*/ NamingException, SQLException{
        
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("tienhuynh-tn");
        Connection con = ds.getConnection();
        
        return con;
        
//        //1. Có drivers -> add driver to library
//        //1.1 Load drivers
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. create url connection string
//        String url = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=PRJ;"
//                + "instanceName=TIENHUYNHTN";
//        //3. Open connection 
//        Connection con = DriverManager.getConnection(url, "sa", "Thuytien");
//
//        return con;
    }
}
