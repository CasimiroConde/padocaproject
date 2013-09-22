package actions;

/**
 * Classe que representa uma ação abstrata de cadastro
 * 
 * @author Marcio Barros
 */
public class RegistrationAction extends Action
{
	/**
	 * Número de itens por página
	 */
	private int pageSize;
	
	/**
	 * Inicializa uma ação de cadastro indicando o número de itens por página
	 */
	public RegistrationAction(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
	/**
	 * Inicializa uma ação de cadastro indicando o número de itens por página
	 */
	public RegistrationAction()
	{
		this(10);
	}
	
	/**
	 * Retorna o número de itens por página
	 */
	protected int getPageSize()
	{
		return pageSize;
	}
	
	/**
	 * Conta o número de páginas necessárias para comportar um número de elementos
	 */
	protected int countToPages(int count)
	{
		return (int) Math.ceil(((double)count) / pageSize);
	}
	
	/**
	 * Adiciona um item de texto no início de um vetor de strings
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
	 * Adiciona um item "Todos" no início de um vetor de strings
	 */
	protected String[] addAll(String[] valores)
	{
		return addString(valores, "Todos");
	}
	
	/**
	 * Adiciona um item em branco no início de um vetor de strings
	 */
	protected String[] addBlank(String[] valores)
	{
		return addString(valores, "");
	}
	
	/**
	 * Monta uma string com as características utilizadas no filtro
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