package padocaproject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@SuppressWarnings("serial")
public class LogoutServlet  extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
            {	
	 
		HttpSession session = req.getSession();
		session.setAttribute("UserName", null);
		session.setAttribute("ID", null);
		
		resp.sendRedirect("Login_Index.jsp");
			
	 
 }
}
