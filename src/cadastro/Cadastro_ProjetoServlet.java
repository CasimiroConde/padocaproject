package cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Query.FilterOperator;

import DAO.DAOFactory;

import padocaproject.Projeto;

@SuppressWarnings("serial")
public class Cadastro_ProjetoServlet  extends HttpServlet {
	
	String message =  " ";
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	                throws IOException, ServletException {	
	
		if(ValidarCampos(req)){
	
			Long IDproj;
			Projeto proj = new Projeto();
			HttpSession session = req.getSession();
			
			
			proj.setNome(req.getParameter("nome_Projeto"));
			proj.setUsuario((Long) session.getAttribute("ID"));
			
	        IDproj = DAOFactory.getProjetoDAO().put(proj);
	        session.setAttribute("IDProj", IDproj);	 
	        
	        resp.sendRedirect("/padocaproject.jsp");
	  		
		}else
		{	
			req.setAttribute("nomeProjeto", req.getParameter("nome_Projeto"));
			req.setAttribute("message", message);
			req.getRequestDispatcher("/padocaproject.jsp").forward(req, resp);
			
		}
	}

public boolean ValidarCampos(HttpServletRequest req){
	
	if(req.getParameter("nome_Projeto").isEmpty()) //Testa Erros de Campos Vazios
	{
		message = "Campos Vazios";
		return false;
	} 
	else //Testa se o nome do projeto já existe para o usuário, caso sim envia erro, caso não exista ele aceita.
	{
		List<Projeto> Projs = new ArrayList<Projeto>();
		HttpSession session = req.getSession();
				
		Projs = DAOFactory.getProjetoDAO().list(DAOFactory.getProjetoDAO().exactFilter("Usuario", FilterOperator.EQUAL, (Long) session.getAttribute("ID")));
		
		for(Projeto p1 : Projs){
			if(req.getParameter("nome_Projeto").equals(p1.getNome())){
				message = "Esse projeto já existe";
				return false;
			}
		}
	}
		return true;
}

}
