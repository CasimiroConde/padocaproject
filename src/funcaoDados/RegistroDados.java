package funcaoDados;

import java.util.ArrayList;
import java.util.List;

public class RegistroDados 
{
	private String nome;
	private List<String> dados;

	public RegistroDados()
	{
		this.dados = new ArrayList<String>();
	}
	
	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public int contaDados()
	{
		return dados.size();
	}
	
	public String pegaDadoIndice(int index)
	{
		return dados.get(index);
	}
	
	public void adicionaDado(String nome)
	{
		dados.add(nome);
	}

	public void removeDado(int index)
	{
		dados.remove(index);
	}
	
	public Iterable<String> getDados() 
	{
		return dados;
	}
}