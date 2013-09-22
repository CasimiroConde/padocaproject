package actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authentication.AuthenticationService;
import authentication.IUser;

import utill.Conversion;

/**
 * Classe que representa uma ação abstrata
 * 
 * @author Marcio Barros
 */
public class Action
{
	/**
	 * Chave de acesso para a lista de notificações na requisição
	 */
	public static final String NOTICES_KEY = "notices";

	/**
	 * Chave de acesso para a lista de erros na requisição
	 */
	public static final String ERRORS_KEY = "errors";

	/**
	 * Tipo de URL para continuidade da ação
	 */
	public static final String ACTION_DEPENDENT = "ACTION_DEPENDENT";
	
	/**
	 * Constante que identifica o resultado de conclusão bem sucedida da ação
	 */
	public static final String SUCCESS = "SUCCESS";

	/**
	 * Constante que identifica o resultado de conclusão mau sucedida da ação
	 */
	public static final String ERROR = "ERROR";

	/**
	 * Constante que identifica o resultado de conclusão bem sucedida sem
	 * redirecionamento
	 */
	public static final String NONE = "";

	/**
	 * Parâmetros que serão adicionado ao retorno gerado pela ação
	 */
	private HashMap<String, String> parameters;

	/**
	 * Lista de erros registrados durante a ação
	 */
	private List<ActionError> errors;

	/**
	 * Lista de avisos registrados durante a ação
	 */
	private List<String> notices;

	/**
	 * Contexto do servlet que disparou a ação
	 */
	private ServletContext servletContext;

	/**
	 * Requisição do servlet que disparou a ação
	 */
	private HttpServletRequest servletRequest;

	/**
	 * Resposta do servlet que disparou a ação
	 */
	private HttpServletResponse servletResponse;
	
	/**
	 * URL de destino da ação para resultados dependentes de ação 
	 */
	private String actionDependentURL;

	/**
	 * Inicializa a ação
	 */
	public Action()
	{
		this.parameters = new HashMap<String, String>();
		this.errors = new ArrayList<ActionError>();
		this.notices = new ArrayList<String>();
		this.actionDependentURL = "";
	}
	
	/**
	 * Programa o contexto do servlet na ação
	 */
	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}
	
	/**
	 * Programa o canal de requisição do servlet na ação
	 */
	public void setServletRequest(HttpServletRequest servletRequest)
	{
		this.servletRequest = servletRequest;
	}
	
	/**
	 * Programa o canal de resposta do servlet na ação
	 */
	public void setServletResponse(HttpServletResponse servletResponse)
	{
		this.servletResponse = servletResponse;
	}
	
	/**
	 * Verifica se a ação registrou algum erro
	 */
	public boolean hasErrors()
	{
		return !errors.isEmpty();
	}
	
	/**
	 * Retorna a lista de erros registrados na ação
	 */
	public Iterable<ActionError> getErrors()
	{
		return errors;
	}
	
	/**
	 * Adiciona um erro gerado durante a ação e associado a um campo
	 * 
	 * @param field Campo associado ao erro
	 * @param message Mensagem associada ao erro
	 */
	public String addError(String field, String message)
	{
		errors.add(new ActionError(field, message));
		return ERROR;
	}

	/**
	 * Adiciona um erro global gerado durante a ação
	 * 
	 * @param message Mensagem associada ao erro
	 */
	public String addError(String message)
	{
		errors.add(new ActionError(message));
		return ERROR;
	}
	
	/**
	 * Verifica se a ação registrou algum aviso
	 */
	public boolean hasNotices()
	{
		return !notices.isEmpty();
	}
	
	/**
	 * Retorna a lista de avisos registrados na ação
	 */
	public Iterable<String> getNotices()
	{
		return notices;
	}

	/**
	 * Adiciona uma mensagem de aviso gerada durante a ação
	 * 
	 * @param message Mensagem de aviso
	 * @return
	 */
	public String addNotice(String message)
	{
		notices.add(message);
		return SUCCESS;
	}

	/**
	 * Retorna o contexto associado ao servlet
	 */
	public ServletContext getContext()
	{
		return servletContext;
	}

	/**
	 * Retorna a requisição feita pelo servlet
	 */
	public HttpServletRequest getRequest()
	{
		return servletRequest;
	}

	/**
	 * Retorna a resposta sendo gerada pelo servlet
	 */
	public HttpServletResponse getResponse()
	{
		return servletResponse;
	}

	/**
	 * Retorna o valor de um cookie
	 */
	public String getCookie(String name)
	{
		Cookie[] cookies = servletRequest.getCookies();
		
		if (cookies == null)
			return null;
		
		for (Cookie cookie : cookies)
			if (cookie.getName().compareTo(name) == 0)
				return cookie.getValue();
		
		return null;
	}
	
	/**
	 * Retorna o valor de um cookie, considerando um valor default
	 */
	public String getCookie(String name, String def)
	{
		String result = getCookie(name);
		return (result != null) ? result : def;
	}
	
	/**
	 * Adiciona um cookie na resposta da ação
	 */
	public void addCookie(String name, String value)
	{
		servletResponse.addCookie(new Cookie(name, value));
	}

	/**
	 * Retorna o valor de um atributo da requisição
	 */
	public Object getAttribute(String name)
	{
		return servletRequest.getAttribute(name);
	}

	/**
	 * Muda o valor de um atributo da requisição
	 */
	public void setAttribute(String name, Object obj)
	{
		servletRequest.setAttribute(name, obj);
	}
	
	/**
	 * Verifica se a ação possui parâmetros
	 */
	public boolean hasRedirectParameters()
	{
		return !parameters.isEmpty();
	}
	
	/**
	 * Retorna os nomes dos parâmetros registrados na ação
	 */
	public Iterable<String> getRedirectParameterNames()
	{
		return parameters.keySet();
	}
	
	/**
	 * Retorna o valor de um parâmetro de redirect, dado seu nome
	 */
	public String getRedirectParameter(String name)
	{
		return parameters.get(name);
	}

	/**
	 * Retorna o valor de um parâmetro
	 * 
	 * @param name Nome do parâmetro desejado
	 */
	public String getParameter(String name)
	{
		String parameter = servletRequest.getParameter(name);
		
		if (parameter != null)
			return parameter.trim();
		
		return null;
	}

	/**
	 * Retorna o valor de um parâmetro
	 * 
	 * @param name Nome do parâmetro desejado
	 */
	public String getParameter(String name, String def)
	{
		String parameter = servletRequest.getParameter(name);
		
		if (parameter != null)
			return parameter.trim();
		
		return def;
	}

	/**
	 * Retorna o valor de um parâmetro inteiro
	 */
	public boolean getBooleanParameter(String name, boolean def)
	{
		String parameter = servletRequest.getParameter(name);
		
		if (parameter != null)
			return Conversion.safeParseBoolean(parameter);
		
		return def;
	}

	/**
	 * Retorna o valor de um parâmetro inteiro
	 */
	public int getIntParameter(String name, int def)
	{
		return Conversion.safeParseInteger(getParameter(name), def);
	}

	/**
	 * Retorna o valor de um parâmetro longo
	 */
	public long getLongParameter(String name, long def)
	{
		return Conversion.safeParseLong(getParameter(name), def);
	}

	/**
	 * Retorna o valor de um parâmetro double
	 */
	public double getDoubleParameter(String name, double def)
	{
		return Conversion.safeParseDouble(getParameter(name), def);
	}
	
	/**
	 * Adiciona um parâmetro do tipo string no retorno da ação
	 * 
	 * @param name Nome do parâmetro
	 * @param value Valor do parâmetro
	 */
	protected void addParameter(String name, String value)
	{
		parameters.put(name, value);
	}

	/**
	 * Adiciona um parâmetro do tipo data no retorno da ação
	 * 
	 * @param name Nome do parâmetro
	 * @param value Valor do parâmetro
	 */
	protected void addParameter(String name, Date value)
	{
		addParameter(name, new SimpleDateFormat("yyyy-MM-dd").format(value));
	}

	/**
	 * Adiciona um parâmetro do tipo inteiro no retorno da ação
	 * 
	 * @param name Nome do parâmetro
	 * @param value Valor do parâmetro
	 */
	protected void addParameter(String name, int value)
	{
		addParameter(name, Integer.toString(value));
	}

	/**
	 * Adiciona um parâmetro do tipo inteiro longo no retorno da ação
	 * 
	 * @param name Nome do parâmetro
	 * @param value Valor do parâmetro
	 */
	protected void addParameter(String name, long value)
	{
		addParameter(name, Long.toString(value));
	}

	/**
	 * Adiciona um parâmetro numérico no retorno da ação
	 * 
	 * @param name Nome do parâmetro
	 * @param value Valor do parâmetro
	 */
	protected void addParameter(String name, double value)
	{
		addParameter(name, Double.toString(value));
	}

	/**
	 * Retorna a URL de destino da ação em caso de resultados dependentes de ação
	 */
	public String getActionDependentURL()
	{
		return this.actionDependentURL;
	}

	/**
	 * Altera a URL de destino da ação em caso de resultados dependentes de ação
	 */
	protected void setActionDependentURL(String url)
	{
		this.actionDependentURL = url;
	}

	/**
	 * Verifica se o usuário está logado para executar a ação
	 */
	protected void checkLogged() throws ActionException
	{
		check(AuthenticationService.getUser(getRequest()) != null, "O usuário deve estar logado para executar esta ação");
	}

	/**
	 * Verifica se o usuário tem permissão de acesso para executar a ação
	 */
	protected void checkUserLevel(String level) throws ActionException
	{
		IUser user = AuthenticationService.getUser(getRequest());
		check(user != null, "O usuário deve estar logado para executar esta ação");
		check(user.checkLevel(level), "O usuário deve ser '" + level + "' para executar esta ação");
	}

	/**
	 * Verifica uma regra de negócio, gerando exceção em caso de falha
	 */
	protected void check(boolean bool, String message) throws ActionException
	{
		if (!bool)
			throw new ActionException(message);
	}

	/**
	 * Verifica uma regra de negócio, gerando exceção em caso de falha
	 */
	protected void check(boolean bool, String field, String message) throws ActionException
	{
		if (!bool)
			throw new ActionException(field, message);
	}

	/**
	 * Verifica uma regra de negócio, gerando exceção em caso de falha
	 */
	protected void checkNonEmpty(String valor, String message) throws ActionException
	{
		check(valor != null && valor.length() > 0, message);
	}

	/**
	 * Verifica uma regra de negócio, gerando exceção em caso de falha
	 */
	protected void checkNonEmpty(String valor, String campo, String message) throws ActionException
	{
		check(valor != null && valor.length() > 0, campo, message);
	}

	/**
	 * Gera um resultado na saída de uma ação
	 */
	protected void write(String s) throws ActionException
	{
		try
		{
			getResponse().getWriter().println(s);
		} catch (IOException e)
		{
			throw new ActionException("Erro ao tentar gerar resposta de ação");
		}
	}

	/**
	 * Gera um resultado no log do sistema
	 */
	protected void log(String s)
	{
		System.out.println(s);
	}
	
	protected Date getDateParameter(String nome)
	{
		String sData = getParameter(nome);
		
		if (sData == null)
			return null;
		
        try
		{
			return new SimpleDateFormat("dd/MM/yyyy").parse(sData);
		} catch (ParseException e)
		{
			return null;
		}
	}
	
	protected int countToPages(int count, int pageSize)
	{
		return (int) Math.ceil(((double)count) / pageSize);
	}
}