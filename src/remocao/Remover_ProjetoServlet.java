package remocao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Query.FilterOperator;

import funcaoDados.FuncaoDados;
import funcaoTransacional.FuncoesTransacionais;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Remover_ProjetoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
    {	
		long id = Long.valueOf (req.getParameter ("id"));
		
		//Remove Projeto
		DAOFactory.getProjetoDAO().remove(id);
		
		
		List<FuncaoDados> listFd = DAOFactory.getFuncaoDadosDAO().list(DAOFactory.getFuncaoDadosDAO().exactFilter("Projeto", FilterOperator.EQUAL, id));
		List<FuncoesTransacionais> listFt = DAOFactory.getFuncaoTransacionalDAO().list(DAOFactory.getFuncaoTransacionalDAO().exactFilter("Projeto", FilterOperator.EQUAL, id));
		
		//Remover Funções de Dados do Projeto
		for(FuncaoDados fd1 : listFd){
			DAOFactory.getFuncaoDadosDAO().remove(fd1.getId());
		}
		
		//Remover funções Transacionais do Projeto
		for(FuncoesTransacionais ft1 : listFt){
			DAOFactory.getFuncaoTransacionalDAO().remove(ft1.getId());
		}
		
		resp.sendRedirect("/padocaproject.jsp");
	
	}
}
