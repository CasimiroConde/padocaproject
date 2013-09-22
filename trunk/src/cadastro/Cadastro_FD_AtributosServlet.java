package cadastro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funcaoDados.FuncaoDados;
import funcaoDados.RegistroDados;

import DAO.DAOFactory;

@SuppressWarnings("serial")
public class Cadastro_FD_AtributosServlet extends HttpServlet {
	
	String message = "";
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException  {
		
		

		long idFuncaoDados = Long.valueOf(req.getParameter("IDFD"));
		FuncaoDados fd = DAOFactory.getFuncaoDadosDAO().get(idFuncaoDados);
		String tipo = req.getParameter("tipo").toString();
		
		if(ValidarCampos(req, fd, tipo)){	
			
			
		
			if(tipo.equals("RET"))
			{
				String nome = req.getParameter("nome_Atributo");
	
				RegistroDados ret = new RegistroDados();
				ret.setNome(nome);
				fd.adicionaRegistro(ret);
				DAOFactory.getFuncaoDadosDAO().put(fd);
				resp.sendRedirect("/Show_FD_Info.jsp?id_proj="+fd.getIdProjeto()+"&id_FD=" +idFuncaoDados);
			}
			else if (tipo.equals("DET"))
			{
				String nomeDado = req.getParameter("nome_Atributo");
				String nomeRegistro = req.getParameter("nome_Ret");
			
				RegistroDados ret = fd.pegaRegistroNome(nomeRegistro);
				ret.adicionaDado(nomeDado);
				DAOFactory.getFuncaoDadosDAO().put(fd);
					
				resp.sendRedirect("/Show_FD_Info.jsp?id_proj="+fd.getIdProjeto()+"&id_FD=" +idFuncaoDados);
			}
		}
		else
		{
			req.setAttribute("nomeAtributo", req.getParameter("nome_Atributo"));
			req.setAttribute("messageError", message);
			req.getRequestDispatcher("/Show_FD_Info.jsp?id_proj="+fd.getIdProjeto()+"&id_FD=" +idFuncaoDados).forward(req, resp);
		}
}

public boolean ValidarCampos(HttpServletRequest req, FuncaoDados fd, String tipo){
	
	String nome = req.getParameter("nome_Atributo");	
	
	
	// Teste se o nome do atributo não é vazio
	if(req.getParameter("nome_Atributo").isEmpty()) 
	{
		message = "Campos Vazios.";
		return false;
	}

	//Testa se o nome do atributo não começa com número
	for(int i =0 ; i <= 9; i++){
		if(nome.startsWith(String.valueOf(i))){
			message = "O nome não pode iniciar com um número.";
		 	return false;
		}
	}
	
	
	/*Testa se o RET é repitido*/
	if(tipo.equals("RET"))
	{
		Iterable<RegistroDados> rets = fd.getRegistros();
		for( RegistroDados ret1 : rets){
			if(ret1.getNome().equals(nome)){
				message = "RET já existe";
				return false;	}
		}
		
	}
	
	//Testa de DET já existe no RET especificado
	if(tipo.equals("DET")){
		String nomeRegistro = req.getParameter("nome_Ret");
		RegistroDados ret = fd.pegaRegistroNome(nomeRegistro);
		int numDet = ret.contaDados();
		
		for(int i = 0; i < numDet; i++){
			if(ret.pegaDadoIndice(i).equals(nome)){
				message = "DET já existe nesse RET";
				return false;
			}
		}	
	}
	
	if(tipo.equals("DET"))
	{
		if(req.getParameter("nome_Ret")==null){
			message = "Favor Cadastrar um RET antes";
			return false;
		}
	}
	
	//Testa o nome do atributo possui espaço
	
	if(req.getParameter("nome_Atributo").indexOf(" ") >= 0){
		message = "O nome não pode possuir espaço";
		return false;
	}
		
	
	return true;
}
	
}
