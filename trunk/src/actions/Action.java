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
 * Classe que representa uma a��o abstrata
 * 
 * @author Marcio Barros
 */
public class Action
{
	/**
	 * Chave de acesso para a lista de notifica��es na requisi��o
	 */
	public static final String NOTICES_KEY = "notices";

	/**
	 * Chave de acesso para a lista de erros na requisi��o
	 */
	public static final String ERRORS_KEY = "errors";

	/**
	 * Tipo de URL para continuidade da a��o
	 */
	public static final String ACTION_DEPENDENT = "ACTION_DEPENDENT";
	
	/**
	 * Constante que identifica o resultado de conclus�o bem sucedida da a��o
	 */
	public static final String SUCCESS = "SUCCESS";

	/**
	 * Constante que identifica o resultado de conclus�o mau sucedida da a��o
	 */
	public static final String ERROR = "ERROR";

	/**
	 * Constante que identifica o resultado de conclus�o bem sucedida sem
	 * redirecionamento
	 */
	public static final String NONE = "";

	/**
	 * Par�metros que ser�o adicionado ao retorno gerado pela a��o
	 */
	private HashMap<String, String> parameters;

	/**
	 * Lista de erros registrados durante a a��o
	 */
	private List<ActionError> errors;

	/**
	 * Lista de avisos registrados durante a a��o
	 */
	private List<String> notices;

	/**
	 * Contexto do servlet que disparou a a��o
	 */
	private ServletContext servletContext;

	/**
	 * Requisi��o do servlet que disparou a a��o
	 */
	private HttpServletRequest servletRequest;

	/**
	 * Resposta do servlet que disparou a a��o
	 */
	private HttpServletResponse servletResponse;
	
	/**
	 * URL de destino da a��o para resultados dependentes de a��o 
	 */
	private String actionDependentURL;

	/**
	 * Inicializa a a��o
	 */
	public Action()
	{
		this.parameters = new HashMap<String, String>();
		this.errors = new ArrayList<ActionError>();
		this.notices = new ArrayList<String>();
		this.actionDependentURL = "";
	}
	
	/**
	 * Programa o contexto do servlet na a��o
	 */
	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}
	
	/**
	 * Programa o canal de requisi��o do servlet na a��o
	 */
	public void setServletRequest(HttpServletRequest servletRequest)
	{
		this.servletRequest = servletRequest;
	}
	
	/**
	 * Programa o canal de resposta do servlet na a��o
	 */
	public void setServletResponse(HttpServletResponse servletResponse)
	{
		this.servletResponse = servletResponse;
	}
	
	/**
	 * Verifica se a a��o registrou algum erro
	 */
	public boolean hasErrors()
	{
		return !errors.isEmpty();
	}
	
	/**
	 * Retorna a lista de erros registrados na a��o
	 */
	public Iterable<ActionError> getErrors()
	{
		return errors;
	}
	
	/**
	 * Adiciona um erro gerado durante a a��o e associado a um campo
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
	 * Adiciona um erro global gerado durante a a��o
	 * 
	 * @param message Mensagem associada ao erro
	 */
	public String addError(String message)
	{
		errors.add(new ActionError(message));
		return ERROR;
	}
	
	/**
	 * Verifica se a a��o registrou algum aviso
	 */
	public boolean hasNotices()
	{
		return !notices.isEmpty();
	}
	
	/**
	 * Retorna a lista de avisos registrados na a��o
	 */
	public Iterable<String> getNotices()
	{
		return notices;
	}

	/**
	 * Adiciona uma mensagem de aviso gerada durante a a��o
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
	 * Retorna a requisi��o feita pelo servlet
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
	 * Adiciona um cookie na resposta da a��o
	 */
	public void addCookie(String name, String value)
	{
		servletResponse.addCookie(new Cookie(name, value));
	}

	/**
	 * Retorna o valor de um atributo da requisi��o
	 */
	public Object getAttribute(String name)
	{
		return servletRequest.getAttribute(name);
	}

	/**
	 * Muda o valor de um atributo da requisi��o
	 */
	public void setAttribute(String name, Object obj)
	{
		servletRequest.setAttribute(name, obj);
	}
	
	/**
	 * Verifica se a a��o possui par�metros
	 */
	public boolean hasRedirectParameters()
	{
		return !parameters.isEmpty();
	}
	
	/**
	 * Retorna os nomes dos par�metros registrados na a��o
	 */
	public Iterable<String> getRedirectParameterNames()
	{
		return parameters.keySet();
	}
	
	/**
	 * Retorna o valor de um par�metro de redirect, dado seu nome
	 */
	public String getRedirectParameter(String name)
	{
		return parameters.get(name);
	}

	/**
	 * Retorna o valor de um par�metro
	 * 
	 * @param name Nome do par�metro desejado
	 */
	public String getParameter(String name)
	{
		String parameter = servletRequest.getParameter(name);
		
		if (parameter != null)
			return parameter.trim();
		
		return null;
	}

	/**
	 * Retorna o valor de um par�metro
	 * 
	 * @param name Nome do par�metro desejado
	 */
	public String getParameter(String name, String def)
	{
		String parameter = servletRequest.getParameter(name);
		
		if (parameter != null)
			return parameter.trim();
		
		return def;
	}

	/**
	 * Retorna o valor de um par�metro inteiro
	 */
	public boolean getBooleanParameter(String name, boolean def)
	{
		String parameter = servletRequest.getParameter(name);
		
		if (parameter != null)
			return Conversion.safeParseBoolean(parameter);
		
		return def;
	}

	/**
	 * Retorna o valor de um par�metro inteiro
	 */
	public int getIntParameter(String name, int def)
	{
		return Conversion.safeParseInteger(getParameter(name), def);
	}

	/**
	 * Retorna o valor de um par�metro longo
	 */
	public long getLongParameter(String name, long def)
	{
		return Conversion.safeParseLong(getParameter(name), def);
	}

	/**
	 * Retorna o valor de um par�metro double
	 */
	public double getDoubleParameter(String name, double def)
	{
		return Conversion.safeParseDouble(getParameter(name), def);
	}
	
	/**
	 * Adiciona um par�metro do tipo string no retorno da a��o
	 * 
	 * @param name Nome do par�metro
	 * @param value Valor do par�metro
	 */
	protected void addParameter(String name, String value)
	{
		parameters.put(name, value);
	}

	/**
	 * Adiciona um par�metro do tipo data no retorno da a��o
	 * 
	 * @param name Nome do par�metro
	 * @param value Valor do par�metro
	 */
	protected void addParameter(String name, Date value)
	{
		addParameter(name, new SimpleDateFormat("yyyy-MM-dd").format(value));
	}

	/**
	 * Adiciona um par�metro do tipo inteiro no retorno da a��o
	 * 
	 * @param name Nome do par�metro
	 * @param value Valor do par�metro
	 */
	protected void addParameter(String name, int value)
	{
		addParameter(name, Integer.toString(value));
	}

	/**
	 * Adiciona um par�metro do tipo inteiro longo no retorno da a��o
	 * 
	 * @param name Nome do par�metro
	 * @param value Valor do par�metro
	 */
	protected void addParameter(String name, long value)
	{
		addParameter(name, Long.toString(value));
	}

	/**
	 * Adiciona um par�metro num�rico no retorno da a��o
	 * 
	 * @param name Nome do par�metro
	 * @param value Valor do par�metro
	 */
	protected void addParameter(String name, double value)
	{
		addParameter(name, Double.toString(value));
	}

	/**
	 * Retorna a URL de destino da a��o em caso de resultados dependentes de a��o
	 */
	public String getActionDependentURL()
	{
		return this.actionDependentURL;
	}

	/**
	 * Altera a URL de destino da a��o em caso de resultados dependentes de a��o
	 */
	protected void setActionDependentURL(String url)
	{
		this.actionDependentURL = url;
	}

	/**
	 * Verifica se o usu�rio est� logado para executar a a��o
	 */
	protected void checkLogged() throws ActionException
	{
		check(AuthenticationService.getUser(getRequest()) != null, "O usu�rio deve estar logado para executar esta a��o");
	}

	/**
	 * Verifica se o usu�rio tem permiss�o de acesso para executar a a��o
	 */
	protected void checkUserLevel(String level) throws ActionException
	{
		IUser user = AuthenticationService.getUser(getRequest());
		check(user != null, "O usu�rio deve estar logado para executar esta a��o");
		check(user.checkLevel(level), "O usu�rio deve ser '" + level + "' para executar esta a��o");
	}

	/**
	 * Verifica uma regra de neg�cio, gerando exce��o em caso de falha
	 */
	protected void check(boolean bool, String message) throws ActionException
	{
		if (!bool)
			throw new ActionException(message);
	}

	/**
	 * Verifica uma regra de neg�cio, gerando exce��o em caso de falha
	 */
	protected void check(boolean bool, String field, String message) throws ActionException
	{
		if (!bool)
			throw new ActionException(field, message);
	}

	/**
	 * Verifica uma regra de neg�cio, gerando exce��o em caso de falha
	 */
	protected void checkNonEmpty(String valor, String message) throws ActionException
	{
		check(valor != null && valor.length() > 0, message);
	}

	/**
	 * Verifica uma regra de neg�cio, gerando exce��o em caso de falha
	 */
	protected void checkNonEmpty(String valor, String campo, String message) throws ActionException
	{
		check(valor != null && valor.length() > 0, campo, message);
	}

	/**
	 * Gera um resultado na sa�da de uma a��o
	 */
	protected void write(String s) throws ActionException
	{
		try
		{
			getResponse().getWriter().println(s);
		} catch (IOException e)
		{
			throw new ActionException("Erro ao tentar gerar resposta de a��o");
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