
package padocaproject;

import java.util.List;

import com.google.appengine.api.datastore.Query.FilterOperator;

import funcaoDados.FuncaoDados;
import funcaoTransacional.FuncoesTransacionais;

import simplemvc.datastore.DataObject;

import DAO.DAOFactory;


public class Projeto implements DataObject {

	private long id;	
	private String nome;	
	private Long usuario;		
	int pontosFuncao;

	
	public Projeto(Long iD, String nome, Long Usuario, List<FuncaoDados> fD,
			List<FuncoesTransacionais> fT) 
	{
		super();
		id = iD;
		this.nome = nome;
		usuario  = Usuario;
		pontosFuncao = 0;
	}
	
	public Projeto() 
	{
		
	}
	
	public int getPontos_Funcao() 
	{
		return pontosFuncao;
	}
	
	public void setPontos_Funcao(int pontos_Funcao) 
	{
		pontosFuncao = pontos_Funcao;
	}

		
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public int Calcular_Funcao_Dados()
	{
		int Pontos_Dados = 0;
		
		List<FuncaoDados> listFd = DAOFactory.getFuncaoDadosDAO().list(DAOFactory.getFuncaoDadosDAO().exactFilter("Projeto", FilterOperator.EQUAL, id));
		
		for(FuncaoDados fd1: listFd){
			Pontos_Dados += fd1.calculaPontosFuncao();
		}
			
		return Pontos_Dados;
	}
	
	public int Calcular_Funcao_Transacionais()
	{
		int Pontos_Transacionais = 0;
		
		List<FuncoesTransacionais> listFt = DAOFactory.getFuncaoTransacionalDAO().list(DAOFactory.getFuncaoTransacionalDAO().exactFilter("Projeto", FilterOperator.EQUAL, id));
		
		for(FuncoesTransacionais ft1: listFt){
			Pontos_Transacionais += ft1.calculaPontosFuncao();
		}
		return Pontos_Transacionais;
	}
	
	public int Calcular_PF()
	{
		int PF = 0;
		PF = this.Calcular_Funcao_Dados() + this.Calcular_Funcao_Transacionais();
		return PF;
	}

	public Long getUsuario() 
	{
		return usuario;
	}

	public void setUsuario(Long usuario) 
	{
		this.usuario = usuario;
	}

	@Override
	public Long getId() 
	{
		return id;
	}

	@Override
	public void setId(Long id) 
	{
		this.id = id;
		
	}
}
