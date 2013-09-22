package cadastro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Query.FilterOperator;

import funcaoDados.FuncaoDados;
import funcaoDados.RegistroDados;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Cadastro_FDServlet extends HttpServlet {

	String message = "";
	
public void doPost(HttpServletRequest req, HttpServletResponse resp)
	                throws IOException, ServletException {
	       	
	long IDproj = Long.valueOf(req.getParameter("IDProj"));
	
	if(ValidarCampos(req, IDproj)){
		FuncaoDados fd = new FuncaoDados();
		fd.setNome(req.getParameter("nome_FD"));
		fd.setTipo(req.getParameter("Tipo_FD"));
		fd.setIdProjeto(IDproj);
		
		//adição de um RET padrão com o nome da própria FD.
		RegistroDados ret = new RegistroDados();
		ret.setNome(req.getParameter("nome_FD"));
		fd.adicionaRegistro(ret);
		
		DAOFactory.getFuncaoDadosDAO().put(fd);  	       		 
		 
		resp.sendRedirect("/Show_Projeto_Info.jsp?id="+ IDproj);
	}else
	{
		req.setAttribute("nomeFD", req.getParameter("nome_FD"));
		req.setAttribute("messageFuncaoDados", message);
		req.getRequestDispatcher("/Show_Projeto_Info.jsp?id="+ IDproj).forward(req, resp);
	}
}
	

public boolean ValidarCampos(HttpServletRequest req, long idProj ){
	
	String nome = req.getParameter("nome_FD");	
	
	if(req.getParameter("nome_FD").isEmpty())
	{
		message = "Campos Vazios";
		return false;
	} 
	
	List<FuncaoDados> listFD = DAOFactory.getFuncaoDadosDAO().list(DAOFactory.getFuncaoDadosDAO().exactFilter("Projeto", FilterOperator.EQUAL, idProj));
	
	for(FuncaoDados fd1 : listFD){
		if(req.getParameter("nome_FD").equals(fd1.getNome())){
			message = "Função já existente";
			return false;
		}
	}
	
	//Testa o nome do atributo possui espaço
	
		if(req.getParameter("nome_FD").indexOf(" ") >= 0){
			message = "O nome não pode possuir espaço";
			return false;
		}
		
		//Testa se o nome do atributo não começa com número
		for(int i =0 ; i <= 9; i++){
			if(nome.startsWith(String.valueOf(i))){
				message = "O nome não pode iniciar com um número.";
			 	return false;
			}
		}
		return true;
}
}
