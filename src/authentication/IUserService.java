package authentication;

/**
 * Classe abstrata dos serviços que devem ser oferecidos para o usuário
 * 
 * @author Marcio Barros
 */
public interface IUserService 
{
	/**
	 * Retorna um usuário, dado seu identificador
	 * 
	 * @param id			Identificador do usuário desejado
	 */
	IUser getUserId(long id);
	
	/**
	 * Retorna um usuário, dado seu e-mail
	 * 
	 * @param email			E-mail do usuário desejado
	 */
	IUser getUserEmail(String email);
	
	/**
	 * Altera a senha do usuário corrente
	 * 
	 * @param usuario		Usuário logado no sistema
	 * @param password		Nova senha do usuário
	 */
	void changePassword(IUser usuario, String password);
}