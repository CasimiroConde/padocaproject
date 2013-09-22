package funcaoTransacional;

import java.util.ArrayList;
import java.util.List;

public class Funcao {
	private String nome;
	private List<String> dados;  // Dados de um FTR, sempre será somado de 1 pelo DET de Mensagens

	public Funcao()
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
