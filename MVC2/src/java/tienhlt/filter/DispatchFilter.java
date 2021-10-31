/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import tienhlt.utils.MyApplicationConstant;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class DispatchFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public DispatchFilter() {
    }    
    
//    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
//            throws IOException, ServletException {
//        if (debug) {
//            log("DispatchFilter:DoBeforeProcessing");
//        }
//
//        // Write code here to process the request and/or response before
//        // the rest of the filter chain is invoked.
//        // For example, a logging filter might log items on the request object,
//        // such as the parameters.
//        /*
//	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
//	    String name = (String)en.nextElement();
//	    String values[] = request.getParameterValues(name);
//	    int n = values.length;
//	    StringBuffer buf = new StringBuffer();
//	    buf.append(name);
//	    buf.append("=");
//	    for(int i=0; i < n; i++) {
//	        buf.append(values[i]);
//	        if (i < n-1)
//	            buf.append(",");
//	    }
//	    log(buf.toString());
//	}
//         */
//    }    
//    
//    private void doAfterProcessing(ServletRequest request, ServletResponse response)
//            throws IOException, ServletException {
//        if (debug) {
//            log("DispatchFilter:DoAfterProcessing");
//        }
//
//        // Write code here to process the request and/or response after
//        // the rest of the filter chain is invoked.
//        // For example, a logging filter might log the attributes on the
//        // request object after the request has been processed. 
//        /*
//	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
//	    String name = (String)en.nextElement();
//	    Object value = request.getAttribute(name);
//	    log("attribute: " + name + "=" + value.toString());
//
//	}
//         */
//        // For example, a filter might append something to the response.
//        /*
//	PrintWriter respOut = new PrintWriter(response.getWriter());
//	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
//         */
//    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String url;
        try {
            //get site map
            ServletContext context = request.getServletContext();
            Properties siteMap = 
                    (Properties) context.getAttribute("SITE_MAP");
            //get resource name
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            //get site mapping
            url = siteMap.getProperty(resource);
//            String action = request.getParameter("btAction");
//            if (action == null) {
//                url = siteMap.getProperty(
//                        MyApplicationConstant.DispatchFeatures.STARTUP_APP_CONTROLLER);
//            }
            if (url != null) {
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Throwable t) {
            log(t.getMessage());
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("DispatchFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DispatchFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatchFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
