package utill;

import java.util.HashMap;

/**
 * Class that represents a hash map of parameters
 * 
 * @author marcio.barros
 */
public class ParameterMap extends HashMap<String, String[]>
{
	private static final long serialVersionUID = 6398910463153103533L;

	/**
	 * Adds a string-valued parameter to the map
	 */
	public ParameterMap add(String name, String value)
	{
		String[] values = this.get(name);
		
		if (values != null)
		{
			String[] newValues = new String[values.length + 1];
			
			for (int i = 0; i < values.length; i++)
				newValues[i] = values[i];
			
			newValues[values.length] = value;
		}
		else
			values = new String[] { value };

		this.put(name, values);
		return this;
	}

	/**
	 * Adds an integer-values parameter to the map
	 */
	public ParameterMap add(String name, int value)
	{
		return this.add(name, Integer.toString(value));
	}
}