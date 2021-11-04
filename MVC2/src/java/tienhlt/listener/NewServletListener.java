package tienhlt.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        loadSiteMap(context);
        context.log("Load site-map success!!!");
        loadAuthenticationFile(context);
        context.log("Load authentication-map success!!!");
        context.log("Deployed!!!!!");
    }
    
    private void loadSiteMap(ServletContext context) {
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
    }
    
    private void loadAuthenticationFile(ServletContext context) {
        String authenticationFileLocation = context.getInitParameter("AUTHENTICATION");
        String pathAuthFile = context.getRealPath("/" + authenticationFileLocation);
        
        //ReadFile
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        List<String> listAuthFile = new ArrayList<>();
        
        try {
            f = new File(pathAuthFile);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {                
                listAuthFile.add(line);
            }
            
            context.setAttribute("AUTH_FILE", listAuthFile);
        } catch (FileNotFoundException ex) {
            context.log("NewServletListener_FileNotFound: " + ex.getMessage());
        } catch (IOException ex) {
            context.log("NewServletListener_IO: " + ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex){
                context.log("NewServletListener_IO: " + ex.getMessage());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Undeploying.....");
    }
}
