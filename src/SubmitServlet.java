
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SubmitServlet
 */
@WebServlet("/submit_servlet")
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	Vector<Message> messagesList = new Vector();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// response.setContentType("type/html");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String message = request.getParameter("message");

		Message messageObject = new Message(name,message,new Date());
		
//		messagesList.addElement(new Message(name, message, new Date()));
//		session.setAttribute("messagesList", messagesList);
		
		DB.setMessageDatabase(messageObject);

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\r\n"
				+ "" + "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Dashboard</title>\r\n" + "</head>\r\n");

		out.println("<body>");
		out.print("<div class=\"d-flex justify-content-between p-2 bg-light mb-3 \">"
				+ "<a class=\"\" href=\"search.html\">Search</a>"
				+ "</div>");

		out.println("<h1 class=\"d-flex justify-content-center p-2 \">Dashboard</h1>");
		
		out.print("<form  action=\"submit_servlet\" method=\"POST\">\r\n"
				+ "   <div class=\"d-flex flex-column pt-5 justify-content-center align-items-center\" >\r\n"
				+ "     <input class=\"mb-2 btn btn-light\" type=\"text\" name=\"name\" placeholder=\"Name\" />\r\n"
				+ "      <input  class=\"mb-3 btn btn-light\" type=\"text\" name=\"message\" placeholder=\"Message\" />\r\n"
				+ "      <input  class=\"mb-3 btn btn-dark\" type=\"submit\" value=\"Submit\" />\r\n" + "</div>\r\n" 
				+ "			\r\n" + "\r\n" + "   \r\n" + "\r\n" + "</form>");
		out.print("<div class=\"d-flex flex-column p-2 justify-content-start align-items-center \">");
		
		for (Message item : DB.getMessageDatabase()) {
			out.print("<div class=\"card m-2 \"  style=\"width:80%\">");
			out.print("<h5 class=\"card-header bg-dark text-white\">" + item.getName() + "</h5>");
			out.print("<div class=\"card-body\"><p class=\"card-text\">" + "  " + item.getMessage() + "</p>");
			out.print("<p class=\"card-text\"><small class=\"text-muted\">" + dateFormat.format(item.getDate())
					+ "</small></p>\r\n" + "</div>");
			out.print("</div>");

		}
		out.print("</div>");

		out.println("</body>");
		out.print("</html>");
		out.close();

	}

}
