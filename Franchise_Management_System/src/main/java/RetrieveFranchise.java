import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RetrieveDoctor
 */
@WebServlet("/RetrieveFranchise")
public class RetrieveFranchise extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveFranchise() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet r = null;

        try {
            c = GetConnection.getConnection();
            if (c == null) {
                out.println("<h1>Database connection failed</h1>");
                return;
            }

            String sql = "SELECT * FROM doctor";
            ps = c.prepareStatement(sql);
            r = ps.executeQuery();

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>FranchiseName</th>");
            out.println("<th>Email</th>");
            out.println("<th>Phone</th>");
            out.println("<th>FranchiseAvl</th>");
            out.println("<th>Reg Date</th>");
            out.println("<th>FranchiseCost</th>");
            out.println("<th>Domain</th>");
//            out.println("<th>Patients Under</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");

            while (r.next()) {
                out.println("<tr>");
                out.println("<td>" + r.getInt("id") + "</td>");
                out.println("<td>" + r.getString("name") + "</td>");
                out.println("<td>" + r.getString("email") + "</td>");
                out.println("<td>" + r.getString("phone") + "</td>");
                out.println("<td>" + r.getInt("age") + "</td>");
                out.println("<td>" + r.getDate("joindate") + "</td>");
                out.println("<td>" + r.getDouble("salary") + "</td>");
                out.println("<td>" + r.getString("specialist") + "</td>");
//                out.println("<td>" + r.getInt("patients") + "</td>");
                out.println("<td><form method=\"GET\" action=\"RetrievePatientsDID\"><input type=\"hidden\" value=\"" + r.getInt("id") + "\" name=\"id\"><input type=\"submit\" value=\"View \"></form></td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (SQLException e) {
            out.println("<h1>Database Error</h1>");
            e.printStackTrace(out);
        } finally {
            // Close resources
            try {
                if (r != null) r.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace(out);
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
