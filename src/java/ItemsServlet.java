/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/ItemsServlet"})
public class ItemsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Cookie[] cookies = request.getCookies();
         String username = "";
         for(Cookie c : cookies){
             String n =c.getName();
             if(n.equals("name")){
                 username=c.getValue();
             }
         }
         response.getWriter().println("Hello "+username);
	 String db = "jdbc:mariadb://localhost/basket7";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn  = DriverManager.getConnection(db);
             Statement stmt = conn.createStatement();
            String query = """
                    SELECT * FROM potrebiteli;
                           """;
            ResultSet rs = stmt.executeQuery(query);
            conn.close();
            while(rs.next()){
                int id = rs.getInt(1);
                String name=rs.getString(2);
                String pass=rs.getString(3);
                String mail=rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
