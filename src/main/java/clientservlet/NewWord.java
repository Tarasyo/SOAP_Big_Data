/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientservlet;

import com.dataaccess.webservicesserver.NumberConversion;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Tarasyo
 */
@WebServlet(name = "NewWord", urlPatterns = {"/NewWord"})
public class NewWord extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/www.dataaccess.com/webservicesserver/NumberConversion.wso.wsdl")
    private NumberConversion service;

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
        
        
         try { // Call Web Service Operation
                com.dataaccess.webservicesserver.NumberConversionSoapType port = service.getNumberConversionSoap();
                // TODO initialize WS operation arguments here
                java.lang.String input = "";
                java.math.BigInteger ubiNum = new java.math.BigInteger(input);
                // TODO process result here
                java.lang.String result = port.numberToWords(ubiNum);
                out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
         TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }

// Now you can access an https URL without having the certificate in the truststore
        try {
            URL url = new URL("https://hostname/index.html");
        } catch (MalformedURLException e) {
        }
         
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          String input = request.getParameter("inputNumber");
            
            
            BigInteger number;
            int n;
            n = Integer.valueOf(input);
            number = BigInteger.valueOf(n);
            
            java.math.BigInteger send = number;
            
            java.lang.String result = numberToWords(send);
            
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewWord</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Magic: " + result + "</h1>");
            out.println("<button onclick=\"location.href = 'http://localhost:8080/SoapCa/';\" id=\"myButton\" class=\"float-left submit-button\" >Home Page</button>");
            out.println("</body>");
            out.println("</html>");
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

    private String numberToWords(java.math.BigInteger ubiNum) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.dataaccess.webservicesserver.NumberConversionSoapType port = service.getNumberConversionSoap();
        return port.numberToWords(ubiNum);
    }
}
