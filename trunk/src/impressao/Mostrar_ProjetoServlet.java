package impressao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import padocaproject.Projeto;
import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Mostrar_ProjetoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {	
	 
	  
	 List<Projeto> lista_Projeto = new ArrayList<Projeto>();
	 
	 lista_Projeto = DAOFactory.getProjetoDAO().list();
	 
	 req.setAttribute("entity", lista_Projeto);
	 
	 for (Projeto l1: lista_Projeto){
		 
		 System.out.println(l1.getNome());
	 }
	 
	 req.getRequestDispatcher("/Show_Projeto.jsp").forward(req, resp);
	 
 }
}
