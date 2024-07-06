import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Retrievevent")
public class Retrievevent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Retrievevent() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection c = GetConnection.getConnection()) {
            String sql = "SELECT * FROM medicine";
            PreparedStatement ps = c.prepareStatement(sql);

            try (ResultSet r = ps.executeQuery()) {
                ResultSetMetaData rms = r.getMetaData();

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>" + rms.getColumnName(1) + "</th>");
                out.println("<th>" + rms.getColumnName(2) + "</th>");
                out.println("<th>" + rms.getColumnName(3) + "</th>");
                out.println("<th>" + rms.getColumnName(4) + "</th>");
                out.println("</tr>");

                while (r.next()) {
                    out.println("<tr>");
                    out.println("<td>" + r.getString("id") + "</td>");
                    out.println("<td>" + r.getString("name") + "</td>");
                    out.println("<td>" + r.getString("price") + "</td>");
                    out.println("<td>" + r.getString("count") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } catch (SQLException e) {
            response.setContentType("text/html");
            out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br></font></h1>");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
