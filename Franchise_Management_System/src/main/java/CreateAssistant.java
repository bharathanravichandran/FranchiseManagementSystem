import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateAssistant")
public class CreateAssistant extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public CreateAssistant() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String joindate = request.getParameter("joindate");
        String pwd = request.getParameter("password");
        PrintWriter out = response.getWriter();
        Connection c = null;
        PreparedStatement ps = null;
        
        try { Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Franchise", "root", "9445617174");
            String sql = "insert into assistant(name,email,phone,joindate,password) values(?,?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, joindate);
            ps.setString(5, pwd);
            
            int successCount = ps.executeUpdate(); // Use executeUpdate() for INSERT operations
            
            if (successCount == 1) {
                response.sendRedirect("login.html");
            } else {
                response.setContentType("text/html");  
                out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br>REDIRECTING BACK REGISTRATION PAGE</font></h1><script type=\"text/javascript\">");  
                out.println("redirectURL = \"newAssistant.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
                out.println("</script>");
            }
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) { 
            e.printStackTrace();
            // Print detailed error message
            out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br>REDIRECTING BACK REGISTRATION PAGE</font></h1><br>");
            out.println("<p>Error Message: " + e.getMessage() + "</p>");
            out.println("<script type=\"text/javascript\">");  
            out.println("redirectURL = \"newAssistant.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
            out.println("</script>");
        } finally {
            // Close resources in finally block
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
