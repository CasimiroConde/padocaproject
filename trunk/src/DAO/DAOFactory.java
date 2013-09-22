package DAO;

import DAO.UsuarioLoginDAO;
import DAO.UsuarioTokenSenhaDAO;

public class DAOFactory {
	
	private static UsuarioDAO usariodao = new UsuarioDAO();
	private static ProjetoDAO projetodao = new ProjetoDAO();
	private static FuncaoDadosDAO fddao = new FuncaoDadosDAO();
	private static FuncaoTransacionalDAO ftdao = new FuncaoTransacionalDAO();
	private static UsuarioLoginDAO loginUsuarioDAO = new UsuarioLoginDAO();
	private static UsuarioTokenSenhaDAO tokenSenhaUsuarioDAO = new UsuarioTokenSenhaDAO();

	public static UsuarioDAO getUsariodao() {
		return usariodao;
	}

	public static void setUsariodao(UsuarioDAO usariodao) {
		DAOFactory.usariodao = usariodao;
	}

	public static UsuarioLoginDAO getLoginUsuarioDAO() {
		return loginUsuarioDAO;
	}

	public static void setLoginUsuarioDAO(UsuarioLoginDAO loginUsuarioDAO) {
		DAOFactory.loginUsuarioDAO = loginUsuarioDAO;
	}

	public static UsuarioTokenSenhaDAO getTokenSenhaUsuarioDAO() {
		return tokenSenhaUsuarioDAO;
	}

	public static void setTokenSenhaUsuarioDAO(
			UsuarioTokenSenhaDAO tokenSenhaUsuarioDAO) {
		DAOFactory.tokenSenhaUsuarioDAO = tokenSenhaUsuarioDAO;
	}

	public static ProjetoDAO getProjetodao() {
		return projetodao;
	}

	public static FuncaoDadosDAO getFddao() {
		return fddao;
	}

	public static FuncaoTransacionalDAO getFtdao() {
		return ftdao;
	}

	public static UsuarioDAO getUsuarioDAO() {
		return usariodao;
	}
	
	public static void setUsuariodao(UsuarioDAO usuariodao) {
		DAOFactory.usariodao = usuariodao;
	}
	
	public static ProjetoDAO getProjetoDAO() {
		return projetodao;
	}
	
	public static void setProjetodao(ProjetoDAO projetodao) {
		DAOFactory.projetodao = projetodao;
	}
	
	public static FuncaoDadosDAO getFuncaoDadosDAO() {
		return fddao;
	}
	
	public static void setFddao(FuncaoDadosDAO fddao) {
		DAOFactory.fddao = fddao;
	}
	
	public static FuncaoTransacionalDAO getFuncaoTransacionalDAO() {
		return ftdao;
	}
	
	public static void setFtdao(FuncaoTransacionalDAO ftdao) {
		DAOFactory.ftdao = ftdao;
	}
	
	
}