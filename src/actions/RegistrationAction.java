package actions;

/**
 * Classe que representa uma a��o abstrata de cadastro
 * 
 * @author Marcio Barros
 */
public class RegistrationAction extends Action
{
	/**
	 * N�mero de itens por p�gina
	 */
	private int pageSize;
	
	/**
	 * Inicializa uma a��o de cadastro indicando o n�mero de itens por p�gina
	 */
	public RegistrationAction(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
	/**
	 * Inicializa uma a��o de cadastro indicando o n�mero de itens por p�gina
	 */
	public RegistrationAction()
	{
		this(10);
	}
	
	/**
	 * Retorna o n�mero de itens por p�gina
	 */
	protected int getPageSize()
	{
		return pageSize;
	}
	
	/**
	 * Conta o n�mero de p�ginas necess�rias para comportar um n�mero de elementos
	 */
	protected int countToPages(int count)
	{
		return (int) Math.ceil(((double)count) / pageSize);
	}
	
	/**
	 * Adiciona um item de texto no in�cio de um vetor de strings
	 */
	private String[] addString(String[] valores, String novaOpcao)
	{
		String[] resultado = new String[valores.length + 1];
		resultado[0] = novaOpcao;
		
		for (int i = 0; i < valores.length; i++)
			resultado[i+1] = valores[i];
		
		return resultado;
	}
	
	/**
	 * Adiciona um item "Todos" no in�cio de um vetor de strings
	 */
	protected String[] addAll(String[] valores)
	{
		return addString(valores, "Todos");
	}
	
	/**
	 * Adiciona um item em branco no in�cio de um vetor de strings
	 */
	protected String[] addBlank(String[] valores)
	{
		return addString(valores, "");
	}
	
	/**
	 * Monta uma string com as caracter�sticas utilizadas no filtro
	 */
	protected String appendFilterText(String filter, String title, String value)
	{
		if (value == null || value.isEmpty())
			return filter;
		
		if (!filter.isEmpty())
			filter += "; ";

		return filter + title + "=" + value; 
	}
}