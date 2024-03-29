package authentication;

/**
 * Classe que representa um usu�rio abstrato
 * 
 * @author Marcio Barros
 */
public interface IUser 
{
	/**
	 * Retorna o identificador do usu�rio
	 */
	public int getId();
	
	/**
	 * Retorna o nome do usu�rio
	 */
	public String getName();
	
	/**
	 * Retorna o e-mail do usu�rio
	 */
	public String getEmail();
	
	/**
	 * Retorna a senha do usu�rio
	 */
	public String getPassword();
	
	/**
	 * Verifica se o usu�rio tem um determinado n�vel de acesso no sistema
	 * 
	 * @param level		N�vel de acesso desejado
	 */
	public boolean checkLevel(String level);
}