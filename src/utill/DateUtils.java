package utill;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utility class for handling dates
 * 
 * @author marcio.barros
 */
public class DateUtils
{
	private static Calendar calendar = new GregorianCalendar();
	
	/**
	 * Returns the year of a given date
	 */
	public static int getYear(Date date)
	{
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * Returns the month of a given date
	 */
	public static int getMonth(Date date)
	{
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * Returns the day of a given date
	 */
	public static int getDay(Date date)
	{
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Returns the hour of a given date
	 */
	public static int getHour(Date data)
	{
		calendar.setTime(data);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Returns the minute of a given date
	 */
	public static int getMinute(Date data)
	{
		calendar.setTime(data);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * Creates a date object, given its components
	 */
	public static Date create(int day, int month, int year)
	{
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return (Date) calendar.getTime().clone();
	}

	/**
	 * Creates a date object, given its components
	 */
	public static Date create(int day, int month, int year, int hour, int minute, int second)
	{
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		return (Date) calendar.getTime().clone();
	}

	/**
	 * Adds a number of days to a date
	 */
	public static Date addDays(Date date, int days)
	{
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	/**
	 * Adds a number of months to a date
	 */
	public static Date addMonths(Date date, int months)
	{
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * Returns the current date
	 */
	public static Date now()
	{
		return new Date();
	}

	/**
	 * Returns the last instant of a date
	 */
	public static Date getLastMoment(Date data)
	{
		int dia = DateUtils.getDay(data);
		int mes = DateUtils.getMonth(data);
		int ano = DateUtils.getYear(data);
		return create(dia, mes, ano, 23, 59, 59);
	}

	/**
	 * Returns the first instant of a date
	 */
	public static Date getFirstMoment(Date data)
	{
		int dia = DateUtils.getDay(data);
		int mes = DateUtils.getMonth(data);
		int ano = DateUtils.getYear(data);
		return create(dia, mes, ano, 0, 0, 0);
	}

	/**
	 * Returns the first day of a month
	 */
	public static Date getFirstDayMonth(int mes, int ano)
	{
		return create(1, mes, ano, 0, 0, 0);
	}

	/**
	 * Checks whether a given year is a leap-year
	 */
	public static boolean isLeapYear(int ano)
	{
		return ((ano % 4 == 0) && (ano % 100 != 0) || (ano % 400 == 0));
	}

	/**
	 * Returns the number of days in a given month/year
	 */
	public static int getMonthDays(int mes, int ano)
	{
		int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};		
		return (mes == 2 && isLeapYear(ano)) ? 29 : monthDays[mes-1];
	}

	/**
	 * Returns the last day of a month
	 */
	public static Date getLastDayMonth(int mes, int ano)
	{
		int dia = getMonthDays(mes, ano);
		return create(dia, mes, ano, 0, 0, 0);
	}

	/**
	 * Returns the name of a month
	 */
	public static String getNomeMes(int mes)
	{
		String monthName[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};		
		return monthName[mes-1];
	}

	/**
	 * Returns a date, given its components
	 */
	public static Date setTempo(Date data, int hora, int minuto, int segundo)
	{
		calendar.setTime(data);
		calendar.add(Calendar.HOUR, hora);
		calendar.add(Calendar.MINUTE, minuto);
		calendar.add(Calendar.SECOND, segundo);
		return calendar.getTime();
	}
}