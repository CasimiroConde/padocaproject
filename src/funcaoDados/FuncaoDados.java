package funcaoDados;

import java.util.ArrayList;
import java.util.List;

import simplemvc.datastore.DataObject;

public class FuncaoDados implements DataObject {

	private long id;
	private String nome;
	private String tipo;
	private long idProjeto;
	private List<RegistroDados> registrosDados;	
			
	public FuncaoDados() 
	{
		registrosDados = new ArrayList<RegistroDados>();
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

	public void setNome(String nome) 
	{
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

	public long getIdProjeto() 
	{
		return idProjeto;
	}

	public void setIdProjeto(long idProjeto) 
	{
		this.idProjeto = idProjeto;
	}
	
	public int contaRegistros()
	{
		return registrosDados.size();
	}
	
	public RegistroDados pegaRegistroIndice(int index)
	{
		return registrosDados.get(index);
	}
	
	public RegistroDados pegaRegistroNome(String nome)
	{
		for (RegistroDados ret : registrosDados)
			if (ret.getNome().compareToIgnoreCase(nome) == 0)
				return ret;
		
		return null;
	}
	
	public void adicionaRegistro(RegistroDados ret)
	{
		registrosDados.add(ret);
	}

	public void removeRegistro(int index)
	{
		registrosDados.remove(index);
	}
	
	public Iterable<RegistroDados> getRegistros() 
	{
		return registrosDados;
	}

	public int calculaPontosFuncao()
	{
		int PF_Dados = 0;
		int numRet = this.contaRegistros(); // Número de RETs da Funcao de Dados
		int numDet = 0; // Número de DETs da Funcao de Dados
		int complexidade; // Complexidade da Funcao de Dados
		String tipoFD = this.getTipo(); // Tipo da Funcao de Dados
		
		Iterable<RegistroDados> listRet = this.getRegistros();
		
		// Calcula o Total de DETs da Funcao de Dados, somando os DETs de cada RET.
		for(RegistroDados ret1 : listRet){
			numDet += ret1.contaDados();
		}
		
		// Calcula a complexidade da Funcao de Dados
		complexidade = this.calcula_FD_Complexidade(numRet, numDet);
		
		// Calcula o total em Pontos de Funcao da Funcao de Dados.
		PF_Dados = this.informa_FD_PF(tipoFD, complexidade);
		
		return PF_Dados;
	}
	
	public int informa_FD_PF(String tipo, int complexidade)
	{
		if(tipo.equals("ILF")){
			switch(complexidade)
			{
			case 1:
					return 7;
			case 2:
					return 10;
			case 3:
					return 15;
			default:
					return 0;
			}
		}
		else if(tipo.equals("EIF"))
		{
			switch(complexidade)
			{
			case 1:
					return 5;
			case 2:
					return 7;
			case 3:
					return 10;
			default:
					return 0;
			}
		}
		
		return 0;
	}
	
	public int calcula_FD_Complexidade(int numRet, int numDet){
		
		int baixa = 1;
		int media = 2;
		int alta = 3;
		
		if (numRet == 1) {
			if (numDet >= 1 && numDet <= 19) {
				return baixa;
			}
			if (numDet >= 20 && numDet <= 50) {
				return baixa;
			}
			if (numDet >= 51) {
				return media;
			}
		} else if (numRet >= 2 && numRet <= 5) {
			if (numDet >= 1 && numDet <= 19) {
				return baixa;
			}
			if (numDet >= 20 && numDet <= 50) {
				return media;
			}
			if (numDet >= 51) {
				return alta;
			}
		} else {
			if (numDet >= 1 && numDet <= 19) {
				return media;
			}
			if (numDet >= 20 && numDet <= 50) {
				return alta;
			}
			if (numDet >= 51) {
				return alta;
			}
		}
		
		return 0;
	}
}