package tienhlt.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */

/**
 * Web application lifecycle listener.
 *
 * @author Huynh Le Thuy Tien
 */
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Deploying.....");
        String siteMapLocation = context.getInitParameter("SITE_MAP_LOCATION");
        InputStream is = null;
        if (siteMapLocation != null) {
            Properties properties = new Properties();
            is = context.getResourceAsStream(siteMapLocation);
            try {
                properties.load(is);
                context.setAttribute("SITE_MAP", properties);
            } catch (IOException ex) {
                context.log("NewServletListener_IO: " + ex.getMessage());
            }
        }//end siteMapLocation is existed
        context.log("Deployed!!!!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Undeploying.....");
    }
}
