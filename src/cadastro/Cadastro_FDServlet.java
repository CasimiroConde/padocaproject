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
		
		//adi��o de um RET padr�o com o nome da pr�pria FD.
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
			message = "Fun��o j� existente";
			return false;
		}
	}
	
	//Testa o nome do atributo possui espa�o
	
		if(req.getParameter("nome_FD").indexOf(" ") >= 0){
			message = "O nome n�o pode possuir espa�o";
			return false;
		}
		
		//Testa se o nome do atributo n�o come�a com n�mero
		for(int i =0 ; i <= 9; i++){
			if(nome.startsWith(String.valueOf(i))){
				message = "O nome n�o pode iniciar com um n�mero.";
			 	return false;
			}
		}
		return true;
}
}
