import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search_servlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Vector<Message> messagesList = (Vector<Message>) session.getAttribute("messagesList");
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		Integer count = messagesList.size();
		String searchFilter = request.getParameterValues("search")[0];

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\r\n"
				+ "" + "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Search</title>\r\n" + "</head>\r\n");
		out.print("<body>");
		out.print("<div class=\"d-flex justify-content-between p-2 bg-light mb-3 \"> "
				+ "<a href=\"search.html\">Back</a></div>\r\n");
		out.print("<div class=\"d-flex flex-column p-2 justify-content-start align-items-center \">");

		if (searchFilter.equals("Search Name")) {
			for (Message item : messagesList) {
				System.out.println(item.getName());
				if (item.getName().toLowerCase().contains(name.toLowerCase())) {
					out.print("<div class=\"card m-2 \"  style=\"width:80%\">");

					out.print("<h5 class=\"card-header bg-dark text-white\">" + item.getName() + "</h5>");
					out.print("<div class=\"card-body\"><p class=\"card-text\">" + "  " + item.getMessage() + "</p>");
					out.print("<p class=\"card-text\"><small class=\"text-muted\">" + dateFormat.format(item.getDate())
							+ "</small></p>\r\n" + "</div>");
					out.print("</div>");
					
					count--;
				} 

			}	
			if(count==messagesList.size()) {
				out.print("<h1>Content Not Found</h1>");
			}
		} else {
			for (Message item : messagesList) {
				if (dateFormat.format(item.getDate()).toString().equals(date)) {
					out.print("<div class=\"card m-2 \"  style=\"width:80%\">");
					out.print("<h5 class=\"card-header bg-dark text-white\">" + item.getName() + "</h5>");
					out.print("<div class=\"card-body\"><p class=\"card-text\">" + "  " + item.getMessage() + "</p>");
					out.print("<p class=\"card-text\"><small class=\"text-muted\">" + dateFormat.format(item.getDate())
							+ "</small></p>\r\n" + "</div>");
					out.print("</div>");

					count--;
				} 
			}
			if(count==messagesList.size()) {
				out.print("<h1>Content Not Found</h1>");
			}

		}
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();

	}

}
