/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abc
 */
public class ControllerServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
              String fname= request.getParameter("firstname");
            String lname= request.getParameter("lastname");
            String rollno= request.getParameter("rollno");
            /* TODO output your page here. You may use following sample code. */
             out.println("hi there");
             URL url = new URL("http://localhost:8080/WebApplication1/webresources/edu.nsexample.entity.student/"+rollno);
                //URL url = new URL("https://graph.facebook.com/v2.12/me?fields=id%2Cname&access_token=EAACEdEose0cBAI7RKdOO6kjw1LBLuOFie7SPvapfIlUp7r5qRjJpQqRp81kjcqi4PO3ISO8U97DszjGCBnw4QEg1RtNWbIcCDQMfJ9BZAZB8FZA3n04I7o7dqX8OAJbDHZB5iSe3IsSeNYduz6KcdZAwW4iB9IIbWUkrudZAVd4xc8ZAYorMjjCwTkr6Nt4hNIK9iqOMmIKsgZDZD");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
                
		//if (conn.getResponseCode() != 200) {
		//	throw new RuntimeException("Failed : HTTP error code : "
		//			+ conn.getResponseCode());
		//}
                if (conn.getResponseCode() == 204) {
			 out.println("<script type=\"text/javascript\">");
                         out.println("alert('You are not enrolled to our institute');");
                         out.println("</script>");
                         //response.sendRedirect("Web Pages/UIPage.jsp");
                         
		}
                
                

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
                
		String output;
		//System.out.println("Output from Server .... \n");
	//	while ((output = br.readLine()) != null) {
	//		System.out.println(output);
          //              System.out.println("\n");
            //    }       
                  // Code to parse the JSON Data.
		       JsonReader rdr = Json.createReader(br);
                       JsonObject ja = rdr.readObject();
//                       System.out.println(ja.getValueType());
                       
                       //System.out.println(ja.size());
                       Set<Map.Entry<String, JsonValue>> s = ja.entrySet();
                      // System.out.println(s.size());
                       Iterator<Map.Entry<String, JsonValue>> i = s.iterator();
                       while(i.hasNext()) {
                           Map.Entry<String, JsonValue> e = i.next();
                           if(e.getValue().getValueType().equals(JsonValue.ValueType.OBJECT)) {
                               JsonObject jo = (JsonObject) e.getValue();
                                Set<Map.Entry<String, JsonValue>> s1 = jo.entrySet();
                               // System.out.println(s1.size());
                                Iterator<Map.Entry<String, JsonValue>> i1 = s1.iterator();
                                 while(i1.hasNext()) {
                                     Map.Entry<String, JsonValue> e1 = i1.next();
                                     out.println("Key: "+ e1.getKey());
                                     out.println("Value: "+e1.getValue());
                                 }
                           }
                           else {
                           out.println(e.getKey());
                           out.println(e.getValue());
                           }
                       }
                       
                       
                        
                        conn.disconnect();
           
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
