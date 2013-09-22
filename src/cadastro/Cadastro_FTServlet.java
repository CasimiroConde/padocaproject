package cadastro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Query.FilterOperator;

import funcaoTransacional.FuncoesTransacionais;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Cadastro_FTServlet extends HttpServlet {

	String message = "";
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
	
		long IDproj = Long.valueOf(req.getParameter("IDProj"));
		
		if(ValidarCampos(req, IDproj)){
			FuncoesTransacionais ft = new FuncoesTransacionais();
			ft.setNome(req.getParameter("nome_FT"));
			ft.setTipo(req.getParameter("Tipo_FT"));
			ft.setProjeto(IDproj);
			
			DAOFactory.getFuncaoTransacionalDAO().put(ft);
		       		 
			resp.sendRedirect("/Show_Projeto_Info.jsp?id="+ IDproj);
		}else
		{
			req.setAttribute("nomeFT", req.getParameter("nome_FT"));
			req.setAttribute("messageFuncaoTransacional", message);
			req.getRequestDispatcher("/Show_Projeto_Info.jsp?id="+ IDproj).forward(req, resp);
		}
		
	
}
	
	
public boolean ValidarCampos(HttpServletRequest req, long idProj){
		
	String nome = req.getParameter("nome_FT");	
		
	if(req.getParameter("nome_FT").isEmpty()){
		message = "Campos Vazios";
		return false;
	} 
	
	List<FuncoesTransacionais> listFT = DAOFactory.getFuncaoTransacionalDAO().list(DAOFactory.getFuncaoTransacionalDAO().exactFilter("Projeto", FilterOperator.EQUAL, idProj));
	
	for(FuncoesTransacionais ft1 : listFT){
		if(req.getParameter("nome_FT").equals(ft1.getNome())){
			message = "Não é Possível Cadastrar Funções de Dados com nomes iguais.";
			return false;
		}
	}
	
	//Testa o nome do atributo possui espaço
	
	if(req.getParameter("nome_FT").indexOf(" ") >= 0){
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
