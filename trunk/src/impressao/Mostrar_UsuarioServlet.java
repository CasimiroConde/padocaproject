package impressao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import padocaproject.Usuario;


import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Mostrar_UsuarioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException 
            {	
	 
	  
	 List<Usuario> lista_usuarios = new ArrayList<Usuario>();
	 lista_usuarios = DAOFactory.getUsuarioDAO().list();
	 
	 req.setAttribute("entity", lista_usuarios);
	 
	 for (Usuario u1: lista_usuarios){
		 
		 System.out.println(u1.getNome());
	 }
	 
	 req.getRequestDispatcher("/Show_Usuario.jsp").forward(req, resp);
	 
 }
}
