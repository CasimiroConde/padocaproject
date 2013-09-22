package funcaoTransacional;

import java.util.ArrayList;
import java.util.List;

import simplemvc.datastore.DataObject;


public class FuncoesTransacionais implements DataObject {

	private long id;
	private String nome;
	private String tipo;
	private Long idProjeto;
	private List<Funcao> funcao;

	
	public FuncoesTransacionais() 
	{
		funcao = new ArrayList<Funcao>();
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

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
	

	public List<Funcao> getFtr() 
	{
		return funcao;
	}

	public void setFtr(List<Funcao> ftr) 
	{
		this.funcao = ftr;
	}

	public Long getIdProjeto() 
	{
		return idProjeto;
	}

	public void setProjeto(Long projeto) 
	{
		this.idProjeto = projeto;
	}
	
	public int contaFuncao()
	{
		return funcao.size();
	}
	
	public Funcao pegaFuncaoIndice(int index)
	{
		return funcao.get(index);
	}
	
	public Funcao pegaFuncaoNome(String nome)
	{
		for (Funcao ftr : funcao)
			if (ftr.getNome().compareToIgnoreCase(nome) == 0)
				return ftr;
		
		return null;
	}
	
	public void adicionaFuncao(Funcao ftr)
	{
		funcao.add(ftr);
	}

	public void removeFuncao(int index)
	{
		funcao.remove(index);
	}
	
	public Iterable<Funcao> getFuncao() 
	{
		return funcao;
	}
	
	public int calculaPontosFuncao()
	{
		int PF_Transacionais = 0;
		int numFtr = this.contaFuncao(); // Número de Ftrs da Funcao Transacional
		int numDet = 1; // Número de DETs da Funcao Transacional, inicia com 1, pelos dets de msg.
		int complexidade; // Complexidade da Funcao Transacional
		String tipoFT = this.getTipo(); // Tipo da Funcao Transacional
		
		Iterable<Funcao> listFtr = this.getFuncao();
		
		// Calcula o Total de DETs da Funcao de Dados, somando os DETs de cada RET.
		for(Funcao ftr1 : listFtr){
			numDet += ftr1.contaDados();
		}
		
		// Calcula a complexidade da Funcao de Dados
		complexidade = this.calcula_FT_Complexidade(numFtr, numDet, tipoFT);
		
		// Calcula o total em Pontos de Funcao da Funcao de Dados.
		PF_Transacionais = this.informa_FT_PF(tipoFT, complexidade);
		
		return PF_Transacionais;
	}
	
public int calcula_FT_Complexidade(int numFtr, int numDet, String tipo){
		
		int baixa = 1;
		int media = 2;
		int alta = 3;
		
		if(tipo.equals("EI"))
		{
			if (numFtr <= 1) {
				if (numDet >= 1 && numDet <= 4) {
					return baixa;
				}
				if (numDet >= 5 && numDet <= 15) {
					return baixa;
				}
				if (numDet >= 16) {
					return media;
				}
			} else if (numFtr == 2) {
				if (numDet >= 1 && numDet <= 4) {
					return baixa;
				}
				if (numDet >= 5 && numDet <= 15) {
					return media;
				}
				if (numDet >= 16) {
					return alta;
				}
			} else if (numFtr > 2) {
				if (numDet >= 1 && numDet <= 4) {
					return media;
				}
				if (numDet >= 5 && numDet <= 15) {
					return alta;
				}
				if (numDet >= 16) {
					return alta;
				}
			}
			
		}
		else if(tipo.equals("EO") || tipo.equals("EQ"))
		{
			if (numFtr <= 1) {
				if (numDet >= 1 && numDet <= 5) {
					return baixa;
				}
				if (numDet >= 6 && numDet <= 19) {
					return baixa;
				}
				if (numDet >= 20) {
					return media;
				}
			} else if (numFtr > 1 && numFtr <= 3) {
				if (numDet >= 1 && numDet <= 5) {
					return baixa;
				}
				if (numDet >= 6 && numDet <= 19) {
					return media;
				}
				if (numDet >= 20) {
					return alta;
				}
			} else if(numFtr > 3){
				if (numDet >= 1 && numDet <= 5) {
					return media;
				}
				if (numDet >= 6 && numDet <= 19) {
					return alta;
				}
				if (numDet >= 20) {
					return alta;
				}
			}
		}
		
		return 0;
	}

public int informa_FT_PF(String tipo, int complexidade)
{
	if(tipo.equals("EI") || tipo.equals("EQ")){
		switch(complexidade)
		{
		case 1:
				return 3;
		case 2:
				return 4;
		case 3:
				return 6;
		default:
				return 0;
		}
	}
	else if(tipo.equals("EO"))
	{
		switch(complexidade)
		{
		case 1:
				return 4;
		case 2:
				return 5;
		case 3:
				return 7;
		default:
				return 0;
		}
	} 

	
	return 0;
}
	
}
