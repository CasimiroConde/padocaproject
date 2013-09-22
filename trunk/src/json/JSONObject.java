package json;


public class JSONObject 
{
	private StringBuilder sb;
	
	public JSONObject()
	{
		this.sb = new StringBuilder("{");
	}
	
	private void comma()
	{
		if (sb.length() > 1)
			sb.append(",");
	}

	public JSONObject add(String name, int value)
	{
		comma();
		sb.append("\"" + name + "\":" + value);
		return this;
	}

	public JSONObject add(String name, long value)
	{
		comma();
		sb.append("\"" + name + "\":" + value);
		return this;
	}

	public JSONObject add(String name, boolean value)
	{
		comma();
		sb.append("\"" + name + "\":" + value);
		return this;
	}

	public JSONObject add(String name, JSONArray value)
	{
		comma();
		sb.append("\"" + name + "\":" + value.toString());
		return this;
	}
	
	public JSONObject add(String name, String value)
	{
		comma();
		sb.append("\"" + name + "\":\"" + value + "\"");
		return this;
	}
	
	public String toString()
	{
		return sb.toString() + "}";
	}
}