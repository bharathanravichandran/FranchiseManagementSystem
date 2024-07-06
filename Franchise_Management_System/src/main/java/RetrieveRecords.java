import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/RetrieveRecords")
public class RetrieveRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveRecords() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		try {
			
		Connection c = GetConnection.getConnection();
		String sql = "select * from patient";
		
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery(sql);

		response.setContentType("text/html");
		ResultSetMetaData rms = r.getMetaData();
		
		out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>FranchiseName</th>");
        out.println("<th>Email</th>");
        out.println("<th>Phone</th>");
        out.println("<th>FranchiseCost</th>");
        out.println("<th>OwnerShip</th>");
        out.println("<th>PaymentAmount</th>");
        out.println("<th>ROI</th>");
        out.println("<th>No.Emp</th>");
        out.println("<th>TotalCost</th>");
//        out.println("<th>Patients Under</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");
			while(r.next()){
				out.println("<tr>");
				out.println("<td></td>");
				out.println("<td>"+ r.getString("id") +"</td>");
				out.println("<td>"+ r.getString("name") +"</td>");
				out.println("<td>"+ r.getString("email") +"</td>");
				out.println("<td>"+ r.getString("phone") +"</td>");
				out.println("<td>"+ r.getString("age") +"</td>");
				out.println("<td>"+ r.getString("gender") +"</td>");
				out.println("<td>"+ r.getString("blood") +"</td>");
				out.println("<td>"+ r.getString("symptom") +"</td>");
				out.println("<td>"+ r.getString("disease") +"</td>");
				out.println("<td>"+ r.getString("doctor") +"</td>");
				//out.println("<td>"+ r.getString("visited") +"</td>");
				out.println("</tr>");
			}
		out.print("</table>");
	} catch (SQLException e) { 
		response.setContentType("text/html");  
		out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br></font></h1><script type=\"text/javascript\">");  
		out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
		out.println("</script>");
		e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
